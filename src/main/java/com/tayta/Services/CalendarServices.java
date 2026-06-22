package com.tayta.Services;

import com.tayta.Entities.Calendar;
import com.tayta.Entities.GuardianElderly;
import com.tayta.Repositories.CalendarRepository;
import com.tayta.Repositories.GuardianElderlyRepository;
import com.tayta.Security.Services.CurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarServices {

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private GuardianElderlyRepository guardianElderlyRepository;

    @Autowired
    private CurrentUserService currentUser;

    public List<Calendar> getAll() {
        // El apoderado ve solo los eventos de sus adultos mayores.
        // NURSE y ADMIN ven todos.
        if (currentUser.isGuardian()) {
            List<Long> ids = guardianElderlyRepository
                    .findByGuardian_User_Username(currentUser.username())
                    .stream()
                    .map(GuardianElderly::getElderly)
                    .filter(java.util.Objects::nonNull)
                    .map(e -> e.getId())
                    .distinct()
                    .toList();
            if (ids.isEmpty()) return List.of();
            return calendarRepository.findByElderly_IdIn(ids);
        }
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
