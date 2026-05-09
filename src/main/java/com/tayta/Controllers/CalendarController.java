package com.tayta.Controllers;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.tayta.Entities.Calendar;
import com.tayta.Services.CalendarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/calendars")
public class CalendarController {

    @Autowired
    private CalendarServices calendarServices;

    @GetMapping
    public List<Calendar> getAll() {
        return calendarServices.getAll();
    }

    @GetMapping("/{id}")
    public Calendar getById(@PathVariable Long id) {
        return calendarServices.getById(id);
    }

    @PostMapping
    public Calendar save(@RequestBody Calendar calendar) {
        return calendarServices.save(calendar);
    }

    @PutMapping("/{id}")
    public Calendar update(@PathVariable Long id, @RequestBody Calendar calendar) {
        return calendarServices.update(id, calendar);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        calendarServices.delete(id);
    }
}

