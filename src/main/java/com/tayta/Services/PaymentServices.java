package com.tayta.Services;

import com.tayta.Entities.Payment;
import com.tayta.Repositories.PaymentRepository;
import com.tayta.Security.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CurrentUserService currentUser;

    public List<Payment> getAll() {
        // Ownership: el ADMIN ve todos; el apoderado solo los suyos.
        if (currentUser.isAdmin()) {
            return paymentRepository.findAll();
        }
        return paymentRepository.findBySubscription_Guardian_User_Username(currentUser.username());
    }

    public Payment getById(Long id) {
        return paymentRepository.findById(id).orElse(null);
    }

    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment update(Long id, Payment payment) {
        payment.setId(id);
        return paymentRepository.save(payment);
    }

    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}
