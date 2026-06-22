package com.tayta.Services;

import com.tayta.Entities.Monitoring;
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
    private CurrentUserService currentUser;

    public List<Monitoring> getAll() {
        // Ownership: el enfermero/a ve solo los monitoreos que registró.
        // ADMIN y GUARDIAN ven todos (aún no hay vínculo apoderado-adulto sembrado).
        if (currentUser.isNurse()) {
            return monitoringRepository.findByNurse_User_Username(currentUser.username());
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
