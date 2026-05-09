package com.tayta.Repositories;

import com.tayta.Entities.HealthCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface HealthCenterRepository extends JpaRepository<HealthCenter, Long> {

    // Q3: Centros de salud con puntuación mayor a 3.5
    @Query("SELECT h FROM HealthCenter h WHERE h.rating > :minRating ORDER BY h.rating DESC")
    List<HealthCenter> findByRatingGreaterThan(@Param("minRating") BigDecimal minRating);

    // Q10: Todos los centros para calcular distancia en el servicio
    @Query("SELECT h FROM HealthCenter h WHERE h.latitude IS NOT NULL AND h.longitude IS NOT NULL")
    List<HealthCenter> findAllWithLocation();
}
