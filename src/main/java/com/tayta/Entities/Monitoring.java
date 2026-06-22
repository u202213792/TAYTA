package com.tayta.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Monitoring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_monitoring")
    private Long id;

    @NotNull(message = "El adulto mayor es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_elderly")
    private Elderly elderly;

    @NotNull(message = "El enfermero/a es obligatorio")
    @ManyToOne
    @JoinColumn(name = "id_nurse")
    private Nurse nurse;

    @Column(name = "vital_signs_status")
    private String vitalSignsStatus;

    @NotNull(message = "La fecha es obligatoria")
    @Column(name = "monitoring_date")
    private LocalDate monitoringDate;

    @Column(name = "monitoring_time")
    private LocalTime monitoringTime;

    @DecimalMin(value = "30.0", message = "Temperatura fuera de rango")
    @DecimalMax(value = "45.0", message = "Temperatura fuera de rango")
    @Column(name = "temperature")
    private BigDecimal temperature;

    @Column(name = "blood_pressure")
    private String bloodPressure;

    @Column(name = "observations")
    private String observations;

    @Column(name = "medicine_status")
    private String medicineStatus;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public String getVitalSignsStatus() {
        return vitalSignsStatus;
    }

    public void setVitalSignsStatus(String vitalSignsStatus) {
        this.vitalSignsStatus = vitalSignsStatus;
    }

    public LocalDate getMonitoringDate() {
        return monitoringDate;
    }

    public void setMonitoringDate(LocalDate monitoringDate) {
        this.monitoringDate = monitoringDate;
    }

    public LocalTime getMonitoringTime() {
        return monitoringTime;
    }

    public void setMonitoringTime(LocalTime monitoringTime) {
        this.monitoringTime = monitoringTime;
    }

    public BigDecimal getTemperature() {
        return temperature;
    }

    public void setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getMedicineStatus() {
        return medicineStatus;
    }

    public void setMedicineStatus(String medicineStatus) {
        this.medicineStatus = medicineStatus;
    }
}
