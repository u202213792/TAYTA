package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Location;
import com.tayta.Services.LocationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationServices locationServices;

    @GetMapping
    public List<Location> getAll() {
        return locationServices.getAll();
    }

    @GetMapping("/{id}")
    public Location getById(@PathVariable Long id) {
        return locationServices.getById(id);
    }

    @PostMapping
    public Location save(@RequestBody Location location) {
        return locationServices.save(location);
    }

    @PutMapping("/{id}")
    public Location update(@PathVariable Long id, @RequestBody Location location) {
        return locationServices.update(id, location);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        locationServices.delete(id);
    }
}

