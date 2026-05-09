package com.tayta.Services;

import com.tayta.Entities.Location;
import com.tayta.Repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServices {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() {
        return locationRepository.findAll();
    }

    public Location getById(Long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public Location update(Long id, Location location) {
        location.setId(id);
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}
