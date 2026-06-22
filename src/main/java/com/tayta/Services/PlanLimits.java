package com.tayta.Services;

/** Límites de cada plan de suscripción (cuántos adultos mayores permite). */
public final class PlanLimits {

    private PlanLimits() {}

    public static final int UNLIMITED = Integer.MAX_VALUE;

    public static int maxElderly(String planType) {
        if (planType == null) return 0;
        return switch (planType.trim().toUpperCase()) {
            case "PREMIUM" -> UNLIMITED;
            case "STANDARD" -> 3;
            case "BASIC" -> 1;
            default -> 0;
        };
    }

    public static java.math.BigDecimal priceFor(String planType) {
        if (planType == null) return java.math.BigDecimal.ZERO;
        return switch (planType.trim().toUpperCase()) {
            case "PREMIUM" -> new java.math.BigDecimal("120.00");
            case "STANDARD" -> new java.math.BigDecimal("90.00");
            case "BASIC" -> new java.math.BigDecimal("60.00");
            default -> java.math.BigDecimal.ZERO;
        };
    }

    public static boolean isValidPlan(String planType) {
        return maxElderly(planType) > 0;
    }
}
