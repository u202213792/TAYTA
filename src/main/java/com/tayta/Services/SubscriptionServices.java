package com.tayta.Services;

import com.tayta.Entities.Guardian;
import com.tayta.Entities.Payment;
import com.tayta.Entities.Subscription;
import com.tayta.Entities.User;
import com.tayta.Repositories.GuardianRepository;
import com.tayta.Repositories.PaymentRepository;
import com.tayta.Repositories.SubscriptionRepository;
import com.tayta.Repositories.UserRepository;
import com.tayta.Security.Config.BusinessException;
import com.tayta.Security.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class SubscriptionServices {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CurrentUserService currentUser;

    /** Contratar un plan: crea la suscripción del apoderado actual y su primer pago. */
    public Subscription subscribe(String planType, String method) {
        if (!PlanLimits.isValidPlan(planType)) {
            throw new BusinessException("Plan no válido.");
        }
        String username = currentUser.username();
        // Buscar el perfil de apoderado; si no existe (usuario registrado antes), crearlo.
        Guardian guardian = guardianRepository.findByUser_Username(username).orElseGet(() -> {
            User u = userRepository.findByUsername(username)
                    .orElseThrow(() -> new BusinessException("Sesión no válida."));
            Guardian g = new Guardian();
            g.setUser(u);
            return guardianRepository.save(g);
        });

        LocalDate today = LocalDate.now();

        // Si ya tiene una suscripción activa, se CAMBIA de plan (upgrade/downgrade);
        // si no, se crea una nueva.
        Subscription s = subscriptionRepository.findByGuardian_Id(guardian.getId()).stream()
                .filter(sub -> "ACTIVE".equalsIgnoreCase(sub.getStatus()))
                .findFirst()
                .orElseGet(() -> {
                    Subscription nueva = new Subscription();
                    nueva.setGuardian(guardian);
                    return nueva;
                });

        s.setPlanType(planType.toUpperCase());
        s.setStartDate(today);
        s.setExpiryDate(today.plusMonths(1));
        s.setStatus("ACTIVE");
        s.setPrice(PlanLimits.priceFor(planType));
        s.setDiscount(BigDecimal.ZERO);
        Subscription saved = subscriptionRepository.save(s);

        Payment p = new Payment();
        p.setSubscription(saved);
        p.setPaymentDate(today);
        p.setPaymentTime(LocalTime.now());
        p.setAmount(saved.getPrice());
        p.setStatus("PAID");
        p.setMethod(method);
        paymentRepository.save(p);

        return saved;
    }

    public List<Subscription> getAll() {
        // Ownership: el ADMIN ve todas; el apoderado solo las suyas.
        if (currentUser.isAdmin()) {
            return subscriptionRepository.findAll();
        }
        return subscriptionRepository.findByGuardian_User_Username(currentUser.username());
    }

    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription save(Subscription subscription) {
        enforceSingleActive(subscription, null);
        return subscriptionRepository.save(subscription);
    }

    public Subscription update(Long id, Subscription subscription) {
        subscription.setId(id);
        enforceSingleActive(subscription, id);
        return subscriptionRepository.save(subscription);
    }

    /** Un apoderado solo puede tener una suscripción ACTIVE a la vez. */
    private void enforceSingleActive(Subscription subscription, Long editingId) {
        if (subscription.getGuardian() == null || subscription.getGuardian().getId() == null) return;
        if (!"ACTIVE".equalsIgnoreCase(subscription.getStatus())) return;

        boolean yaTieneActiva = subscriptionRepository.findByGuardian_Id(subscription.getGuardian().getId()).stream()
                .anyMatch(s -> "ACTIVE".equalsIgnoreCase(s.getStatus())
                        && (editingId == null || !s.getId().equals(editingId)));

        if (yaTieneActiva) {
            throw new BusinessException("Este apoderado ya tiene una suscripción activa.");
        }
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
