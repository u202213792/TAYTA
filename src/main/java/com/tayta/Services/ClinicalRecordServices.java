package com.tayta.Services;

import com.tayta.Entities.ClinicalRecord;
import com.tayta.Repositories.ClinicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClinicalRecordServices {

    @Autowired
    private ClinicalRecordRepository clinicalRecordRepository;

    public List<ClinicalRecord> getAll() {
        return clinicalRecordRepository.findAll();
    }

    public ClinicalRecord getById(Long id) {
        return clinicalRecordRepository.findById(id).orElse(null);
    }

    public ClinicalRecord save(ClinicalRecord clinicalRecord) {
        return clinicalRecordRepository.save(clinicalRecord);
    }

    public ClinicalRecord update(Long id, ClinicalRecord clinicalRecord) {
        clinicalRecord.setId(id);
        return clinicalRecordRepository.save(clinicalRecord);
    }

    public void delete(Long id) {
        clinicalRecordRepository.deleteById(id);
    }
}
