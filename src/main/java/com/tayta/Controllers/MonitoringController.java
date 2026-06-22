package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Monitoring;
import com.tayta.Services.MonitoringServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/monitoring")
public class MonitoringController {

    @Autowired
    private MonitoringServices monitoringServices;

    @GetMapping
    public List<Monitoring> getAll() {
        return monitoringServices.getAll();
    }

    @GetMapping("/{id}")
    public Monitoring getById(@PathVariable Long id) {
        return monitoringServices.getById(id);
    }

    @PostMapping
    public Monitoring save(@Valid @RequestBody Monitoring monitoring) {
        return monitoringServices.save(monitoring);
    }

    @PutMapping("/{id}")
    public Monitoring update(@PathVariable Long id, @Valid @RequestBody Monitoring monitoring) {
        return monitoringServices.update(id, monitoring);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        monitoringServices.delete(id);
    }
}

