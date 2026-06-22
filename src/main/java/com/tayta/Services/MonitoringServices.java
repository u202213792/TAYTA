package com.tayta.Services;

import com.tayta.Entities.GuardianElderly;
import com.tayta.Entities.Monitoring;
import com.tayta.Repositories.GuardianElderlyRepository;
import com.tayta.Repositories.MonitoringRepository;
import com.tayta.Security.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringServices {

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Autowired
    private GuardianElderlyRepository guardianElderlyRepository;

    @Autowired
    private CurrentUserService currentUser;

    public List<Monitoring> getAll() {
        // Ownership:
        // - NURSE: solo los monitoreos que registró.
        // - GUARDIAN: solo los de sus adultos mayores.
        // - ADMIN: todos.
        if (currentUser.isNurse()) {
            return monitoringRepository.findByNurse_User_Username(currentUser.username());
        }
        if (currentUser.isGuardian()) {
            List<Long> ids = guardianElderlyRepository
                    .findByGuardian_User_Username(currentUser.username())
                    .stream()
                    .map(GuardianElderly::getElderly)
                    .filter(java.util.Objects::nonNull)
                    .map(e -> e.getId())
                    .distinct()
                    .toList();
            if (ids.isEmpty()) return List.of();
            return monitoringRepository.findByElderly_IdIn(ids);
        }
        return monitoringRepository.findAll();
    }

    public Monitoring getById(Long id) {
        return monitoringRepository.findById(id).orElse(null);
    }

    public Monitoring save(Monitoring monitoring) {
        return monitoringRepository.save(monitoring);
    }

    public Monitoring update(Long id, Monitoring monitoring) {
        monitoring.setId(id);
        return monitoringRepository.save(monitoring);
    }

    public void delete(Long id) {
        monitoringRepository.deleteById(id);
    }
}
