package com.tayta.Dtos;

import java.math.BigDecimal;

public class LocationDto {

    private Long id;
    private Long elderlyId;
    private Long healthCenterId;
    private BigDecimal latitude;
    private BigDecimal longitude;

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

    public Long getHealthCenterId() {
        return healthCenterId;
    }

    public void setHealthCenterId(Long healthCenterId) {
        this.healthCenterId = healthCenterId;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
