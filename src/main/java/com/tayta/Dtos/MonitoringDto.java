package com.tayta.Dtos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class MonitoringDto {

    private Long id;
    private Long elderlyId;
    private Long nurseId;
    private String vitalSignsStatus;
    private LocalDate monitoringDate;
    private LocalTime monitoringTime;
    private BigDecimal temperature;
    private String bloodPressure;
    private String observations;
    private String medicineStatus;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElderlyId() {
        return elderlyId;
    }

    public void setElderlyId(Long elderlyId) {
        this.elderlyId = elderlyId;
    }

    public Long getNurseId() {
        return nurseId;
    }

    public void setNurseId(Long nurseId) {
        this.nurseId = nurseId;
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
