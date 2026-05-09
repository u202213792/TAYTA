package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Nurse;
import com.tayta.Services.NurseServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/nurses")
public class NurseController {

    @Autowired
    private NurseServices nurseServices;

    @GetMapping
    public List<Nurse> getAll() {
        return nurseServices.getAll();
    }

    @GetMapping("/{id}")
    public Nurse getById(@PathVariable Long id) {
        return nurseServices.getById(id);
    }

    @PostMapping
    public Nurse save(@RequestBody Nurse nurse) {
        return nurseServices.save(nurse);
    }

    @PutMapping("/{id}")
    public Nurse update(@PathVariable Long id, @RequestBody Nurse nurse) {
        return nurseServices.update(id, nurse);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        nurseServices.delete(id);
    }
}

