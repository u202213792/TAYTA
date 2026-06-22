package com.tayta.Services;

import com.tayta.Entities.Elderly;
import com.tayta.Entities.GuardianElderly;
import com.tayta.Repositories.ElderlyRepository;
import com.tayta.Repositories.GuardianElderlyRepository;
import com.tayta.Repositories.GuardianRepository;
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
        Elderly saved = elderlyRepository.save(elderly);
        // Si lo crea un apoderado, se vincula automáticamente a él.
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

    public Elderly update(Long id, Elderly elderly) {
        elderly.setId(id);
        return elderlyRepository.save(elderly);
    }

    public void delete(Long id) {
        elderlyRepository.deleteById(id);
    }
}
