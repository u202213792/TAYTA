package com.tayta.Dtos.querys;

import java.math.BigDecimal;

// Q10: Centros de salud más cercanos a la ubicación
public class NearestHealthCentersQueryDto {

    private Long id;
    private String centerName;
    private String address;
    private String emergencyPhone;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private BigDecimal rating;
    private Double distanceKm;

    public NearestHealthCentersQueryDto() {}

    public NearestHealthCentersQueryDto(Long id, String centerName, String address,
                                        String emergencyPhone, BigDecimal latitude,
                                        BigDecimal longitude, BigDecimal rating,
                                        Double distanceKm) {
        this.id = id;
        this.centerName = centerName;
        this.address = address;
        this.emergencyPhone = emergencyPhone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.rating = rating;
        this.distanceKm = distanceKm;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
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

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Double getDistanceKm() {
        return distanceKm;
    }

    public void setDistanceKm(Double distanceKm) {
        this.distanceKm = distanceKm;
    }
}
