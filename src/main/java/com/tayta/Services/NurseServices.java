package com.tayta.Services;

import com.tayta.Entities.Nurse;
import com.tayta.Repositories.NurseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseServices {

    @Autowired
    private NurseRepository nurseRepository;

    public List<Nurse> getAll() {
        return nurseRepository.findAll();
    }

    public Nurse getById(Long id) {
        return nurseRepository.findById(id).orElse(null);
    }

    public Nurse save(Nurse nurse) {
        return nurseRepository.save(nurse);
    }

    public Nurse update(Long id, Nurse nurse) {
        nurse.setId(id);
        return nurseRepository.save(nurse);
    }

    public void delete(Long id) {
        nurseRepository.deleteById(id);
    }
}
