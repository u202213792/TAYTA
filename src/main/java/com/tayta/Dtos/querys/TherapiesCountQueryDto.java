package com.tayta.Dtos.querys;

import java.time.LocalDate;

// Q6: Cantidad de terapias en un rango determinado de tiempo (startDate, endDate)
public class TherapiesCountQueryDto {

    private LocalDate startDate;
    private LocalDate endDate;
    private Long count;

    public TherapiesCountQueryDto() {}

    public TherapiesCountQueryDto(LocalDate startDate, LocalDate endDate, Long count) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.count = count;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}