package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Role;
import com.tayta.Services.RoleServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired
    private RoleServices roleServices;

    @GetMapping
    public List<Role> getAll() {
        return roleServices.getAll();
    }

    @GetMapping("/{id}")
    public Role getById(@PathVariable Long id) {
        return roleServices.getById(id);
    }

    @PostMapping
    public Role save(@RequestBody Role role) {
        return roleServices.save(role);
    }

    @PutMapping("/{id}")
    public Role update(@PathVariable Long id, @RequestBody Role role) {
        return roleServices.update(id, role);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        roleServices.delete(id);
    }
}

