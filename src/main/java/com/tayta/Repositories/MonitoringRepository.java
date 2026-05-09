package com.tayta.Repositories;

import com.tayta.Entities.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {

    // Q4: Cantidad de monitoreos en un rango de tiempo
    @Query("SELECT COUNT(m) FROM Monitoring m WHERE m.monitoringDate BETWEEN :startDate AND :endDate")
    Long countByMonitoringDateBetween(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);
}
