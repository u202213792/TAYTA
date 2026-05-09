package com.tayta.Dtos.querys;

import java.math.BigDecimal;
import java.time.LocalDate;


public class AccumulatedPaymentQueryDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalAmount;

    public AccumulatedPaymentQueryDto() {}

    public AccumulatedPaymentQueryDto(LocalDate startDate, LocalDate endDate, BigDecimal totalAmount) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalAmount = totalAmount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}