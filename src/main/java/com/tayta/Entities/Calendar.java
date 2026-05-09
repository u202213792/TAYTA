package com.tayta.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Calendar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_calendar")
    private Long id;

    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Column(name = "appointment_time")
    private LocalTime appointmentTime;

    @Column(name = "medicine_time")
    private LocalTime medicineTime;

    @Column(name = "medicine_date")
    private LocalDate medicineDate;

    @Column(name = "therapy_date")
    private LocalDate therapyDate;

    @Column(name = "therapy_time")
    private LocalTime therapyTime;

    @Column(name = "vaccines")
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
