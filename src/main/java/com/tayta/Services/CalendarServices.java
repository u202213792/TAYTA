package com.tayta.Services;

import com.tayta.Entities.Calendar;
import com.tayta.Repositories.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServices {

    @Autowired
    private CalendarRepository calendarRepository;

    public List<Calendar> getAll() {
        return calendarRepository.findAll();
    }

    public Calendar getById(Long id) {
        return calendarRepository.findById(id).orElse(null);
    }

    public Calendar save(Calendar calendar) {
        return calendarRepository.save(calendar);
    }

    public Calendar update(Long id, Calendar calendar) {
        calendar.setId(id);
        return calendarRepository.save(calendar);
    }

    public void delete(Long id) {
        calendarRepository.deleteById(id);
    }
}
