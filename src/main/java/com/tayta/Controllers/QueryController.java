package com.tayta.Controllers;

import com.tayta.Dtos.querys.*;
import com.tayta.Services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/queries")
public class QueryController {

    @Autowired
    private QueryService queryService;

    // Q1: GET /api/queries/users-registered?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/users-registered")
    public ResponseEntity<UsersRegisteredQueryDto> getUsersRegistered(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countUsersRegistered(startDate, endDate));
    }

    // Q2: GET /api/queries/accumulated-payment?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/accumulated-payment")
    public ResponseEntity<AccumulatedPaymentQueryDto> getAccumulatedPayment(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.getAccumulatedPayment(startDate, endDate));
    }

    // Q3: GET /api/queries/high-rating-health-centers
    @GetMapping("/high-rating-health-centers")
    public ResponseEntity<List<HighRatingHealthCenterQueryDto>> getHighRatingHealthCenters() {
        return ResponseEntity.ok(queryService.getHighRatingHealthCenters());
    }

    // Q4: GET /api/queries/monitoring-count?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/monitoring-count")
    public ResponseEntity<MonitoringCountQueryDto> getMonitoringCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countMonitorings(startDate, endDate));
    }

    // Q5: GET /api/queries/appointments-count?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/appointments-count")
    public ResponseEntity<AppointmentsCountQueryDto> getAppointmentsCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countAppointments(startDate, endDate));
    }

    // Q6: GET /api/queries/therapies-count?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/therapies-count")
    public ResponseEntity<TherapiesCountQueryDto> getTherapiesCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countTherapies(startDate, endDate));
    }

    // Q7: GET /api/queries/vaccines-count?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/vaccines-count")
    public ResponseEntity<VaccinesCountQueryDto> getVaccinesCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countVaccines(startDate, endDate));
    }

    // Q8: GET /api/queries/active-subscriptions
    @GetMapping("/active-subscriptions")
    public ResponseEntity<ActiveSubscriptionsQueryDto> getActiveSubscriptions() {
        return ResponseEntity.ok(queryService.countActiveSubscriptions());
    }

    // Q9: GET /api/queries/subscriptions-count?startDate=2024-01-01&endDate=2024-12-31
    @GetMapping("/subscriptions-count")
    public ResponseEntity<SubscriptionsCountQueryDto> getSubscriptionsCount(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return ResponseEntity.ok(queryService.countSubscriptions(startDate, endDate));
    }

    // Q10: GET /api/queries/nearest-health-centers?lat=-12.046374&lon=-77.042793&limit=5
    @GetMapping("/nearest-health-centers")
    public ResponseEntity<List<NearestHealthCentersQueryDto>> getNearestHealthCenters(
            @RequestParam double lat,
            @RequestParam double lon,
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(queryService.getNearestHealthCenters(lat, lon, limit));
    }
}
