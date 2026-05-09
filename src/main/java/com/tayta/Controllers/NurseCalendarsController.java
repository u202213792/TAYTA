package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.NurseCalendars;
import com.tayta.Services.NurseCalendarsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/nurse-calendars")
public class NurseCalendarsController {

    @Autowired
    private NurseCalendarsServices nurseCalendarsServices;

    @GetMapping
    public List<NurseCalendars> getAll() {
        return nurseCalendarsServices.getAll();
    }

    @GetMapping("/{id}")
    public NurseCalendars getById(@PathVariable Long id) {
        return nurseCalendarsServices.getById(id);
    }

    @PostMapping
    public NurseCalendars save(@RequestBody NurseCalendars nurseCalendars) {
        return nurseCalendarsServices.save(nurseCalendars);
    }

    @PutMapping("/{id}")
    public NurseCalendars update(@PathVariable Long id, @RequestBody NurseCalendars nurseCalendars) {
        return nurseCalendarsServices.update(id, nurseCalendars);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        nurseCalendarsServices.delete(id);
    }
}

