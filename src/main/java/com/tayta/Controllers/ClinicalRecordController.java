package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.ClinicalRecord;
import com.tayta.Services.ClinicalRecordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/clinical-records")
public class ClinicalRecordController {

    @Autowired
    private ClinicalRecordServices clinicalRecordServices;

    @GetMapping
    public List<ClinicalRecord> getAll() {
        return clinicalRecordServices.getAll();
    }

    @GetMapping("/{id}")
    public ClinicalRecord getById(@PathVariable Long id) {
        return clinicalRecordServices.getById(id);
    }

    @PostMapping
    public ClinicalRecord save(@RequestBody ClinicalRecord clinicalRecord) {
        return clinicalRecordServices.save(clinicalRecord);
    }

    @PutMapping("/{id}")
    public ClinicalRecord update(@PathVariable Long id, @RequestBody ClinicalRecord clinicalRecord) {
        return clinicalRecordServices.update(id, clinicalRecord);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        clinicalRecordServices.delete(id);
    }
}

