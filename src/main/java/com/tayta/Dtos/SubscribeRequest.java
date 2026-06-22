package com.tayta.Dtos;

import jakarta.validation.constraints.NotBlank;

public class SubscribeRequest {

    @NotBlank(message = "El plan es obligatorio")
    private String planType;

    @NotBlank(message = "El método de pago es obligatorio")
    private String method;

    public String getPlanType() { return planType; }
    public void setPlanType(String planType) { this.planType = planType; }

    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
}
