package com.tayta.Repositories;

import com.tayta.Entities.Monitoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public interface MonitoringRepository extends JpaRepository<Monitoring, Long> {

    // Monitoreos registrados por el enfermero/a (ownership)
    List<Monitoring> findByNurse_User_Username(String username);

    // Monitoreos de un conjunto de adultos mayores (ownership del apoderado)
    List<Monitoring> findByElderly_IdIn(Collection<Long> elderlyIds);

    // Monitoreos recientes (para gráficos / datos de demo)
    List<Monitoring> findByMonitoringDateGreaterThanEqual(LocalDate date);

    // Q4: Cantidad de monitoreos en un rango de tiempo
    @Query("SELECT COUNT(m) FROM Monitoring m WHERE m.monitoringDate BETWEEN :startDate AND :endDate")
    Long countByMonitoringDateBetween(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);
}
