package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.GuardianElderly;
import com.tayta.Services.GuardianElderlyServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/guardian-elderly")
public class GuardianElderlyController {

    @Autowired
    private GuardianElderlyServices guardianElderlyServices;

    @GetMapping
    public List<GuardianElderly> getAll() {
        return guardianElderlyServices.getAll();
    }

    @GetMapping("/{id}")
    public GuardianElderly getById(@PathVariable Long id) {
        return guardianElderlyServices.getById(id);
    }

    @PostMapping
    public GuardianElderly save(@RequestBody GuardianElderly guardianElderly) {
        return guardianElderlyServices.save(guardianElderly);
    }

    @PutMapping("/{id}")
    public GuardianElderly update(@PathVariable Long id, @RequestBody GuardianElderly guardianElderly) {
        return guardianElderlyServices.update(id, guardianElderly);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        guardianElderlyServices.delete(id);
    }
}

