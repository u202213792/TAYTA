package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Elderly;
import com.tayta.Services.ElderlyServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/elderly")
public class ElderlyController {

    @Autowired
    private ElderlyServices elderlyServices;

    @GetMapping
    public List<Elderly> getAll() {
        return elderlyServices.getAll();
    }

    @GetMapping("/{id}")
    public Elderly getById(@PathVariable Long id) {
        return elderlyServices.getById(id);
    }

    @PostMapping
    public Elderly save(@Valid @RequestBody Elderly elderly) {
        return elderlyServices.save(elderly);
    }

    @PutMapping("/{id}")
    public Elderly update(@PathVariable Long id, @Valid @RequestBody Elderly elderly) {
        return elderlyServices.update(id, elderly);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        elderlyServices.delete(id);
    }
}

