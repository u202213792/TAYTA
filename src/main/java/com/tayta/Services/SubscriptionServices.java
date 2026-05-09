package com.tayta.Services;

import com.tayta.Entities.Subscription;
import com.tayta.Repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServices {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    public Subscription getById(Long id) {
        return subscriptionRepository.findById(id).orElse(null);
    }

    public Subscription save(Subscription subscription) {
        return subscriptionRepository.save(subscription);
    }

    public Subscription update(Long id, Subscription subscription) {
        subscription.setId(id);
        return subscriptionRepository.save(subscription);
    }

    public void delete(Long id) {
        subscriptionRepository.deleteById(id);
    }
}
