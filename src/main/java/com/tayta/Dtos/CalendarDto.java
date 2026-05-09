package com.tayta.Dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class CalendarDto {

    private Long id;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
    private LocalTime medicineTime;
    private LocalDate medicineDate;
    private LocalDate therapyDate;
    private LocalTime therapyTime;
    private String vaccines;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public LocalTime getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(LocalTime appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public LocalTime getMedicineTime() {
        return medicineTime;
    }

    public void setMedicineTime(LocalTime medicineTime) {
        this.medicineTime = medicineTime;
    }

    public LocalDate getMedicineDate() {
        return medicineDate;
    }

    public void setMedicineDate(LocalDate medicineDate) {
        this.medicineDate = medicineDate;
    }

    public LocalDate getTherapyDate() {
        return therapyDate;
    }

    public void setTherapyDate(LocalDate therapyDate) {
        this.therapyDate = therapyDate;
    }

    public LocalTime getTherapyTime() {
        return therapyTime;
    }

    public void setTherapyTime(LocalTime therapyTime) {
        this.therapyTime = therapyTime;
    }

    public String getVaccines() {
        return vaccines;
    }

    public void setVaccines(String vaccines) {
        this.vaccines = vaccines;
    }
}
