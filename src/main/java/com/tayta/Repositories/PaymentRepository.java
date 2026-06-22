package com.tayta.Repositories;

import com.tayta.Entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Pagos del apoderado (ownership), vía su suscripción
    List<Payment> findBySubscription_Guardian_User_Username(String username);

    // Q2: Monto pago acumulado en un rango de tiempo
    @Query("SELECT COALESCE(SUM(p.amount), 0) FROM Payment p WHERE p.paymentDate BETWEEN :startDate AND :endDate")
    BigDecimal sumAmountByPaymentDateBetween(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);
}
