package com.tayta.Services;

import com.tayta.Entities.Elderly;
import com.tayta.Repositories.ElderlyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderlyServices {

    @Autowired
    private ElderlyRepository elderlyRepository;

    public List<Elderly> getAll() {
        return elderlyRepository.findAll();
    }

    public Elderly getById(Long id) {
        return elderlyRepository.findById(id).orElse(null);
    }

    public Elderly save(Elderly elderly) {
        return elderlyRepository.save(elderly);
    }

    public Elderly update(Long id, Elderly elderly) {
        elderly.setId(id);
        return elderlyRepository.save(elderly);
    }

    public void delete(Long id) {
        elderlyRepository.deleteById(id);
    }
}
