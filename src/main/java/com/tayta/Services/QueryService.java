package com.tayta.Services;

import com.tayta.Dtos.querys.*;
import com.tayta.Entities.HealthCenter;
import com.tayta.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private HealthCenterRepository healthCenterRepository;

    @Autowired
    private MonitoringRepository monitoringRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    // Q1: Cantidad de usuarios registrados en un rango de tiempo
    public UsersRegisteredQueryDto countUsersRegistered(LocalDate startDate, LocalDate endDate) {
        Long count = userRepository.countByCreatedAtBetween(startDate, endDate);
        return new UsersRegisteredQueryDto(startDate, endDate, count);
    }

    // Q2: Monto pago acumulado en un rango de tiempo
    public AccumulatedPaymentQueryDto getAccumulatedPayment(LocalDate startDate, LocalDate endDate) {
        BigDecimal total = paymentRepository.sumAmountByPaymentDateBetween(startDate, endDate);
        return new AccumulatedPaymentQueryDto(startDate, endDate, total);
    }

    // Q3: Centros de salud con puntuación mayor a 3.5
    public List<HighRatingHealthCenterQueryDto> getHighRatingHealthCenters() {
        List<HealthCenter> centers = healthCenterRepository.findByRatingGreaterThan(new BigDecimal("3.5"));
        return centers.stream()
                .map(h -> new HighRatingHealthCenterQueryDto(
                        h.getId(), h.getCenterName(), h.getAddress(),
                        h.getEmergencyPhone(), h.getLatitude(), h.getLongitude(), h.getRating()))
                .collect(Collectors.toList());
    }

    // Q4: Cantidad de monitoreos en un rango de tiempo
    public MonitoringCountQueryDto countMonitorings(LocalDate startDate, LocalDate endDate) {
        Long count = monitoringRepository.countByMonitoringDateBetween(startDate, endDate);
        return new MonitoringCountQueryDto(startDate, endDate, count);
    }

    // Q5: Cantidad de citas en un rango de tiempo
    public AppointmentsCountQueryDto countAppointments(LocalDate startDate, LocalDate endDate) {
        Long count = calendarRepository.countAppointmentsByDateBetween(startDate, endDate);
        return new AppointmentsCountQueryDto(startDate, endDate, count);
    }

    // Q6: Cantidad de terapias en un rango de tiempo
    public TherapiesCountQueryDto countTherapies(LocalDate startDate, LocalDate endDate) {
        Long count = calendarRepository.countTherapiesByDateBetween(startDate, endDate);
        return new TherapiesCountQueryDto(startDate, endDate, count);
    }

    // Q7: Cantidad de vacunas en un rango de tiempo
    public VaccinesCountQueryDto countVaccines(LocalDate startDate, LocalDate endDate) {
        Long count = calendarRepository.countVaccinesByDateBetween(startDate, endDate);
        return new VaccinesCountQueryDto(startDate, endDate, count);
    }

    // Q8: Cantidad de suscripciones en estado activo
    public ActiveSubscriptionsQueryDto countActiveSubscriptions() {
        Long count = subscriptionRepository.countActiveSubscriptions();
        return new ActiveSubscriptionsQueryDto(count);
    }

    // Q9: Cantidad de suscripciones en un rango de tiempo
    public SubscriptionsCountQueryDto countSubscriptions(LocalDate startDate, LocalDate endDate) {
        Long count = subscriptionRepository.countByStartDateBetween(startDate, endDate);
        return new SubscriptionsCountQueryDto(startDate, endDate, count);
    }

    // Q10: Centros de salud más cercanos a una ubicación (fórmula de Haversine)
    public List<NearestHealthCentersQueryDto> getNearestHealthCenters(double userLat, double userLon, int limit) {
        List<HealthCenter> centers = healthCenterRepository.findAllWithLocation();
        return centers.stream()
                .map(h -> {
                    double dist = haversineDistance(userLat, userLon,
                            h.getLatitude().doubleValue(), h.getLongitude().doubleValue());
                    return new NearestHealthCentersQueryDto(
                            h.getId(), h.getCenterName(), h.getAddress(),
                            h.getEmergencyPhone(), h.getLatitude(), h.getLongitude(),
                            h.getRating(), dist);
                })
                .sorted((a, b) -> Double.compare(a.getDistanceKm(), b.getDistanceKm()))
                .limit(limit)
                .collect(Collectors.toList());
    }

    // Fórmula de Haversine para calcular distancia en km entre dos coordenadas
    private double haversineDistance(double lat1, double lon1, double lat2, double lon2) {
        final int EARTH_RADIUS_KM = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        return EARTH_RADIUS_KM * 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    }
}
