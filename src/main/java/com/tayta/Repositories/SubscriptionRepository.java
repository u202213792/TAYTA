package com.tayta.Repositories;

import com.tayta.Entities.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    // Q8: Cantidad de suscripciones en estado activo
    @Query("SELECT COUNT(s) FROM Subscription s WHERE UPPER(s.status) = 'ACTIVE'")
    Long countActiveSubscriptions();

    // Q9: Cantidad de suscripciones en un rango de tiempo
    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.startDate BETWEEN :startDate AND :endDate")
    Long countByStartDateBetween(@Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);
}
