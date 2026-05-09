package com.tayta.Dtos;

public class ClinicalRecordDto {

    private Long id;
    private Long healthCenterId;
    private Long monitoringId;
    private String medicalHistory;
    private String description;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(Long healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public Long getMonitoringId() {
        return monitoringId;
    }

    public void setMonitoringId(Long monitoringId) {
        this.monitoringId = monitoringId;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
