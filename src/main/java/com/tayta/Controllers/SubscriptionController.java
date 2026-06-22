package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Dtos.SubscribeRequest;
import com.tayta.Entities.Subscription;
import com.tayta.Services.SubscriptionServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionServices subscriptionServices;

    @GetMapping
    public List<Subscription> getAll() {
        return subscriptionServices.getAll();
    }

    @GetMapping("/{id}")
    public Subscription getById(@PathVariable Long id) {
        return subscriptionServices.getById(id);
    }

    @PostMapping
    public Subscription save(@RequestBody Subscription subscription) {
        return subscriptionServices.save(subscription);
    }

    // Contratar un plan (el apoderado actual): crea suscripción + pago
    @PostMapping("/subscribe")
    public Subscription subscribe(@Valid @RequestBody SubscribeRequest request) {
        return subscriptionServices.subscribe(request.getPlanType(), request.getMethod());
    }

    @PutMapping("/{id}")
    public Subscription update(@PathVariable Long id, @RequestBody Subscription subscription) {
        return subscriptionServices.update(id, subscription);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        subscriptionServices.delete(id);
    }
}

