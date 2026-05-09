package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.User;
import com.tayta.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @GetMapping
    public List<User> getAll() {
        return userServices.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userServices.getById(id);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userServices.save(user);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        return userServices.update(id, user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userServices.delete(id);
    }
}

