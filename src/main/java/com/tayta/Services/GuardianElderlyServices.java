package com.tayta.Services;

import com.tayta.Entities.GuardianElderly;
import com.tayta.Repositories.GuardianElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuardianElderlyServices {

    @Autowired
    private GuardianElderlyRepository guardianElderlyRepository;

    public List<GuardianElderly> getAll() {
        return guardianElderlyRepository.findAll();
    }

    public GuardianElderly getById(Long id) {
        return guardianElderlyRepository.findById(id).orElse(null);
    }

    public GuardianElderly save(GuardianElderly guardianElderly) {
        return guardianElderlyRepository.save(guardianElderly);
    }

    public GuardianElderly update(Long id, GuardianElderly guardianElderly) {
        guardianElderly.setId(id);
        return guardianElderlyRepository.save(guardianElderly);
    }

    public void delete(Long id) {
        guardianElderlyRepository.deleteById(id);
    }
}
