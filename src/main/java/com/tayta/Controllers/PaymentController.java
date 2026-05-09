package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Payment;
import com.tayta.Services.PaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentServices paymentServices;

    @GetMapping
    public List<Payment> getAll() {
        return paymentServices.getAll();
    }

    @GetMapping("/{id}")
    public Payment getById(@PathVariable Long id) {
        return paymentServices.getById(id);
    }

    @PostMapping
    public Payment save(@RequestBody Payment payment) {
        return paymentServices.save(payment);
    }

    @PutMapping("/{id}")
    public Payment update(@PathVariable Long id, @RequestBody Payment payment) {
        return paymentServices.update(id, payment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        paymentServices.delete(id);
    }
}

