package com.tayta.Services;

import com.tayta.Entities.Elderly;
import com.tayta.Entities.GuardianElderly;
import com.tayta.Entities.Subscription;
import com.tayta.Repositories.ElderlyRepository;
import com.tayta.Repositories.GuardianElderlyRepository;
import com.tayta.Repositories.GuardianRepository;
import com.tayta.Repositories.SubscriptionRepository;
import com.tayta.Security.Config.BusinessException;
import com.tayta.Security.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyServices {

    @Autowired
    private ElderlyRepository elderlyRepository;

    @Autowired
    private GuardianElderlyRepository guardianElderlyRepository;

    @Autowired
    private GuardianRepository guardianRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CurrentUserService currentUser;

    public List<Elderly> getAll() {
        // Ownership: el apoderado ve solo los adultos mayores vinculados a él.
        // ADMIN y NURSE ven todos.
        if (currentUser.isGuardian()) {
            return guardianElderlyRepository
                    .findByGuardian_User_Username(currentUser.username())
                    .stream()
                    .map(GuardianElderly::getElderly)
                    .filter(java.util.Objects::nonNull)
                    .distinct()
                    .toList();
        }
        return elderlyRepository.findAll();
    }

    public Elderly getById(Long id) {
        return elderlyRepository.findById(id).orElse(null);
    }

    public Elderly save(Elderly elderly) {
        // Si lo crea un apoderado, validar el límite de su plan antes de guardar.
        if (currentUser.isGuardian()) {
            enforceElderlyLimit(currentUser.username());
        }

        Elderly saved = elderlyRepository.save(elderly);

        // Y vincularlo automáticamente a él.
        if (currentUser.isGuardian()) {
            guardianRepository.findByUser_Username(currentUser.username()).ifPresent(guardian -> {
                GuardianElderly link = new GuardianElderly();
                link.setGuardian(guardian);
                link.setElderly(saved);
                guardianElderlyRepository.save(link);
            });
        }
        return saved;
    }

    /** Verifica que el apoderado no supere el máximo de adultos mayores de su plan activo. */
    private void enforceElderlyLimit(String username) {
        int current = guardianElderlyRepository.findByGuardian_User_Username(username).size();

        int max = subscriptionRepository.findByGuardian_User_Username(username).stream()
                .filter(s -> "ACTIVE".equalsIgnoreCase(s.getStatus()))
                .mapToInt(s -> PlanLimits.maxElderly(s.getPlanType()))
                .max()
                .orElse(0);

        if (max == 0) {
            throw new BusinessException("Necesitas una suscripción activa para registrar un adulto mayor.");
        }
        if (current >= max) {
            throw new BusinessException(
                    "Tu plan permite hasta " + max + " adulto(s) mayor(es). Mejora tu plan para registrar más.");
        }
    }

    public Elderly update(Long id, Elderly elderly) {
        elderly.setId(id);
        return elderlyRepository.save(elderly);
    }

    public void delete(Long id) {
        elderlyRepository.deleteById(id);
    }
}
