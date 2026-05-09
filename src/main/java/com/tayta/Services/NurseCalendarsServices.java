package com.tayta.Services;

import com.tayta.Entities.NurseCalendars;
import com.tayta.Repositories.NurseCalendarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NurseCalendarsServices {

    @Autowired
    private NurseCalendarsRepository nurseCalendarsRepository;

    public List<NurseCalendars> getAll() {
        return nurseCalendarsRepository.findAll();
    }

    public NurseCalendars getById(Long id) {
        return nurseCalendarsRepository.findById(id).orElse(null);
    }

    public NurseCalendars save(NurseCalendars nurseCalendars) {
        return nurseCalendarsRepository.save(nurseCalendars);
    }

    public NurseCalendars update(Long id, NurseCalendars nurseCalendars) {
        nurseCalendars.setId(id);
        return nurseCalendarsRepository.save(nurseCalendars);
    }

    public void delete(Long id) {
        nurseCalendarsRepository.deleteById(id);
    }
}
