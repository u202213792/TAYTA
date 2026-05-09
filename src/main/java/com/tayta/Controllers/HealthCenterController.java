package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.HealthCenter;
import com.tayta.Services.HealthCenterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/health-centers")
public class HealthCenterController {

    @Autowired
    private HealthCenterServices healthCenterServices;

    @GetMapping
    public List<HealthCenter> getAll() {
        return healthCenterServices.getAll();
    }

    @GetMapping("/{id}")
    public HealthCenter getById(@PathVariable Long id) {
        return healthCenterServices.getById(id);
    }

    @PostMapping
    public HealthCenter save(@RequestBody HealthCenter healthCenter) {
        return healthCenterServices.save(healthCenter);
    }

    @PutMapping("/{id}")
    public HealthCenter update(@PathVariable Long id, @RequestBody HealthCenter healthCenter) {
        return healthCenterServices.update(id, healthCenter);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        healthCenterServices.delete(id);
    }
}

