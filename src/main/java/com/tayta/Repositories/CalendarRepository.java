package com.tayta.Repositories;

import com.tayta.Entities.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    // Q5: Cantidad de citas en un rango de tiempo
    @Query("SELECT COUNT(c) FROM Calendar c WHERE c.appointmentDate BETWEEN :startDate AND :endDate")
    Long countAppointmentsByDateBetween(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    // Q6: Cantidad de terapias en un rango de tiempo
    @Query("SELECT COUNT(c) FROM Calendar c WHERE c.therapyDate BETWEEN :startDate AND :endDate")
    Long countTherapiesByDateBetween(@Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    // Q7: Cantidad de vacunas en un rango de tiempo (filtra por vacunas registradas dentro del rango de appointmentDate)
    @Query("SELECT COUNT(c) FROM Calendar c WHERE c.vaccines IS NOT NULL AND c.appointmentDate BETWEEN :startDate AND :endDate")
    Long countVaccinesByDateBetween(@Param("startDate") LocalDate startDate,
                                    @Param("endDate") LocalDate endDate);
}
