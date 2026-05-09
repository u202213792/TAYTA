package com.tayta.Services;

import com.tayta.Entities.HealthCenter;
import com.tayta.Repositories.HealthCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HealthCenterServices {

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    public List<HealthCenter> getAll() {
        return healthCenterRepository.findAll();
    }

    public HealthCenter getById(Long id) {
        return healthCenterRepository.findById(id).orElse(null);
    }

    public HealthCenter save(HealthCenter healthCenter) {
        return healthCenterRepository.save(healthCenter);
    }

    public HealthCenter update(Long id, HealthCenter healthCenter) {
        healthCenter.setId(id);
        return healthCenterRepository.save(healthCenter);
    }

    public void delete(Long id) {
        healthCenterRepository.deleteById(id);
    }
}
