package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Guardian;
import com.tayta.Services.GuardianServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/guardians")
public class GuardianController {

    @Autowired
    private GuardianServices guardianServices;

    @GetMapping
    public List<Guardian> getAll() {
        return guardianServices.getAll();
    }

    @GetMapping("/{id}")
    public Guardian getById(@PathVariable Long id) {
        return guardianServices.getById(id);
    }

    @PostMapping
    public Guardian save(@RequestBody Guardian guardian) {
        return guardianServices.save(guardian);
    }

    @PutMapping("/{id}")
    public Guardian update(@PathVariable Long id, @RequestBody Guardian guardian) {
        return guardianServices.update(id, guardian);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        guardianServices.delete(id);
    }
}

