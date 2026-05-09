package com.tayta.Services;

import com.tayta.Entities.Guardian;
import com.tayta.Repositories.GuardianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianServices {

    @Autowired
    private GuardianRepository guardianRepository;

    public List<Guardian> getAll() {
        return guardianRepository.findAll();
    }

    public Guardian getById(Long id) {
        return guardianRepository.findById(id).orElse(null);
    }

    public Guardian save(Guardian guardian) {
        return guardianRepository.save(guardian);
    }

    public Guardian update(Long id, Guardian guardian) {
        guardian.setId(id);
        return guardianRepository.save(guardian);
    }

    public void delete(Long id) {
        guardianRepository.deleteById(id);
    }
}
