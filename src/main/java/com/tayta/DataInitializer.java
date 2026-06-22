package com.tayta;

import com.tayta.Entities.*;
import com.tayta.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private RoleRepository roleRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private GuardianRepository guardianRepository;
    @Autowired private NurseRepository nurseRepository;
    @Autowired private ElderlyRepository elderlyRepository;
    @Autowired private HealthCenterRepository healthCenterRepository;
    @Autowired private SubscriptionRepository subscriptionRepository;
    @Autowired private PaymentRepository paymentRepository;
    @Autowired private MonitoringRepository monitoringRepository;
    @Autowired private CalendarRepository calendarRepository;
    @Autowired private GuardianElderlyRepository guardianElderlyRepository;
    @Autowired private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) {
        // Vínculos apoderado↔adulto mayor (idempotente; corre aunque la BD ya esté sembrada)
        seedGuardianElderlyLinks();
        // Datos recientes para la demo (monitoreos y eventos con fechas actuales)
        seedRecentDemoData();

        if (roleRepository.count() > 0) return;

        // ── ROLES ──────────────────────────────────────────────────
        Role roleAdmin    = role("ADMIN");
        Role roleGuardian = role("GUARDIAN");
        Role roleNurse    = role("NURSE");

        // ── USERS (con createdAt para Q1) ─────────────────────────
        // 2024: 4 usuarios
        User u1 = user("admin",     "admin@tayta.com",    roleAdmin,    "2024-01-15");
        User u2 = user("guardian1", "guard1@tayta.com",   roleGuardian, "2024-03-10");
        User u3 = user("guardian2", "guard2@tayta.com",   roleGuardian, "2024-06-20");
        User u4 = user("nurse1",    "nurse1@tayta.com",   roleNurse,    "2024-09-05");
        // 2025: 3 usuarios
        User u5 = user("guardian3", "guard3@tayta.com",   roleGuardian, "2025-01-08");
        User u6 = user("guardian4", "guard4@tayta.com",   roleGuardian, "2025-03-22");
        User u7 = user("nurse2",    "nurse2@tayta.com",   roleNurse,    "2025-04-11");

        // ── GUARDIANS ─────────────────────────────────────────────
        Guardian g1 = guardian(u2, "987654321", "Av. Arequipa 123", "Hijo",    "45678901");
        Guardian g2 = guardian(u3, "912345678", "Jr. Cusco 456",    "Hija",    "52341678");
        Guardian g3 = guardian(u5, "923456789", "Av. Brasil 789",   "Sobrino", "61234578");
        Guardian g4 = guardian(u6, "934567890", "Calle Lima 321",   "Nieto",   "70123456");

        // ── NURSES ────────────────────────────────────────────────
        Nurse n1 = nurse(u4, "ENF-001-2024");
        Nurse n2 = nurse(u7, "ENF-002-2025");

        // ── ELDERLY ───────────────────────────────────────────────
        Elderly e1 = elderly(u1, "12345678", "O+", "M", "1.70", "Ninguna",    "75.5", "Diabetes",    "Metformina",  "Paciente estable");
        Elderly e2 = elderly(u2, "23456789", "A+", "F", "1.60", "Penicilina", "60.0", "Hipertensión","Enalapril",   "Control mensual");

        // ── HEALTH CENTERS (Lima) para Q3 y Q10 ───────────────────
        // Rating > 3.5 (aparecen en Q3)
        healthCenter("Hospital Nacional Dos de Mayo",  "-12.059900", "-77.021900", "01-3280000", "Av. Grau 13, Cercado de Lima",    "4.2");
        healthCenter("Clínica Ricardo Palma",          "-12.106800", "-77.022600", "01-2240400", "Av. Javier Prado Este 1066, San Isidro", "4.5");
        healthCenter("Hospital Rebagliati",            "-12.078900", "-77.049000", "01-2654901", "Av. Rebagliati 490, Jesús María",  "4.0");
        healthCenter("Hospital Loayza",                "-12.054100", "-77.039100", "01-6138989", "Av. Alfonso Ugarte 848, Cercado",  "3.8");
        // Rating < 3.5 (no aparecen en Q3)
        healthCenter("Posta Médica San Juan",          "-12.090000", "-77.060000", "01-5551234", "Jr. Puno 100, La Victoria",        "3.0");
        healthCenter("Centro de Salud Santa Rosa",     "-12.120000", "-77.035000", "01-5559876", "Av. Colonial 200, Callao",         "2.5");

        // ── SUBSCRIPTIONS para Q8 y Q9 ────────────────────────────
        // ACTIVE
        Subscription s1 = subscription(g1, "PREMIUM", "2024-01-01", "2025-01-01", "ACTIVE",   "120.00", "10.00");
        Subscription s2 = subscription(g2, "BASIC",   "2024-06-15", "2025-06-15", "ACTIVE",   "60.00",  "0.00");
        Subscription s3 = subscription(g3, "PREMIUM", "2025-01-10", "2026-01-10", "ACTIVE",   "120.00", "15.00");
        // INACTIVE / EXPIRED
        Subscription s4 = subscription(g4, "BASIC",   "2023-12-01", "2024-12-01", "INACTIVE", "60.00",  "0.00");
        Subscription s5 = subscription(g1, "STANDARD","2025-03-01", "2026-03-01", "ACTIVE",   "90.00",  "5.00");

        // ── PAYMENTS para Q2 ──────────────────────────────────────
        // 2024
        payment(s1, "2024-01-05", "110.00", "PAID");
        payment(s2, "2024-06-20", "60.00",  "PAID");
        payment(s1, "2024-07-05", "110.00", "PAID");
        payment(s4, "2024-01-10", "60.00",  "PAID");
        // 2025
        payment(s3, "2025-01-15", "105.00", "PAID");
        payment(s5, "2025-03-05", "85.00",  "PAID");
        payment(s3, "2025-04-15", "105.00", "PAID");

        // ── MONITORINGS para Q4 ───────────────────────────────────
        // 2024
        monitoring(e1, n1, "NORMAL",  "2024-02-10", "09:00", "36.5", "120/80", "Sin novedades",  "AL DÍA");
        monitoring(e1, n1, "NORMAL",  "2024-05-15", "10:00", "36.7", "118/78", "Paciente estable","AL DÍA");
        monitoring(e2, n1, "ALERTA",  "2024-08-20", "08:30", "37.2", "140/90", "Presión elevada", "PENDIENTE");
        // 2025
        monitoring(e1, n2, "NORMAL",  "2025-01-12", "09:30", "36.6", "119/79", "Sin novedades",  "AL DÍA");
        monitoring(e2, n2, "NORMAL",  "2025-02-28", "11:00", "36.8", "122/82", "Mejora notable",  "AL DÍA");
        monitoring(e1, n2, "NORMAL",  "2025-04-10", "09:00", "36.5", "117/77", "Estable",         "AL DÍA");

        // ── CALENDARS para Q5 (citas), Q6 (terapias), Q7 (vacunas)
        // 2024
        calendar("2024-03-10", "10:00", "2024-03-10", "08:00", "2024-03-15", "14:00", "Influenza");
        calendar("2024-05-20", "11:00", "2024-05-20", "09:00", "2024-05-25", "15:00", null);
        calendar("2024-07-08", "09:30", "2024-07-08", "08:00", "2024-07-12", "14:30", "Neumococo");
        calendar("2024-10-15", "10:00", "2024-10-15", "09:00", "2024-10-20", "15:00", "Tetanos");
        // 2025
        calendar("2025-01-20", "10:00", "2025-01-20", "08:30", "2025-01-25", "14:00", null);
        calendar("2025-02-14", "11:30", "2025-02-14", "09:00", "2025-02-20", "15:00", "Influenza");
        calendar("2025-04-05", "09:00", "2025-04-05", "08:00", "2025-04-10", "14:00", null);

        // Tras crear datos en BD nueva, sembrar los vínculos y datos recientes
        seedGuardianElderlyLinks();
        seedRecentDemoData();
    }

    /**
     * Datos recientes (fechas actuales) para la presentación: monitoreos para los
     * gráficos/actividad y un par de eventos de calendario próximos, vinculados al
     * primer adulto mayor. Idempotente: solo siembra si no hay datos recientes.
     */
    private void seedRecentDemoData() {
        java.util.List<Elderly> elderlyList = elderlyRepository.findAll();
        java.util.List<Nurse> nurses = nurseRepository.findAll();
        if (elderlyList.isEmpty() || nurses.isEmpty()) return;

        LocalDate today = LocalDate.now();
        Elderly e = elderlyList.get(0);
        Nurse n = nurses.get(0);

        boolean hayMonitoreosRecientes =
                !monitoringRepository.findByMonitoringDateGreaterThanEqual(today.minusDays(40)).isEmpty();
        if (!hayMonitoreosRecientes) {
            recentMonitoring(e, n, "NORMAL", today.minusDays(28), "36.5", "118/78", "AL DÍA", "Control estable");
            recentMonitoring(e, n, "NORMAL", today.minusDays(21), "36.7", "120/80", "AL DÍA", "Sin novedades");
            recentMonitoring(e, n, "ALERTA", today.minusDays(14), "37.4", "140/90", "PENDIENTE", "Presión elevada");
            recentMonitoring(e, n, "NORMAL", today.minusDays(7),  "36.6", "122/82", "AL DÍA", "Mejora notable");
            recentMonitoring(e, n, "NORMAL", today.minusDays(2),  "36.4", "117/77", "AL DÍA", "Estable");
            recentMonitoring(e, n, "NORMAL", today,               "36.5", "119/79", "AL DÍA", "Control del día");
        }

        boolean hayCalendarioReciente = calendarRepository
                .findByElderly_IdIn(elderlyList.stream().map(Elderly::getId).toList())
                .stream()
                .anyMatch(c -> c.getAppointmentDate() != null && !c.getAppointmentDate().isBefore(today.minusDays(40)));
        if (!hayCalendarioReciente) {
            calendarFor(e, today.plusDays(2), "10:00", today.plusDays(2), "08:00", today.plusDays(5), "15:00", "Influenza");
            calendarFor(e, today.plusDays(9), "11:30", today.plusDays(9), "09:00", null, null, null);
        }
    }

    private void recentMonitoring(Elderly e, Nurse n, String status, LocalDate date,
                                  String temp, String bp, String med, String obs) {
        Monitoring m = new Monitoring();
        m.setElderly(e);
        m.setNurse(n);
        m.setVitalSignsStatus(status);
        m.setMonitoringDate(date);
        m.setMonitoringTime(LocalTime.of(9, 0));
        m.setTemperature(new BigDecimal(temp));
        m.setBloodPressure(bp);
        m.setMedicineStatus(med);
        m.setObservations(obs);
        monitoringRepository.save(m);
    }

    private void calendarFor(Elderly e, LocalDate ad, String at, LocalDate md, String mt,
                             LocalDate td, String tt, String vac) {
        Calendar c = new Calendar();
        c.setElderly(e);
        if (ad != null) { c.setAppointmentDate(ad); c.setAppointmentTime(LocalTime.parse(at)); }
        if (md != null) { c.setMedicineDate(md); c.setMedicineTime(LocalTime.parse(mt)); }
        if (td != null) { c.setTherapyDate(td); c.setTherapyTime(LocalTime.parse(tt)); }
        c.setVaccines(vac);
        calendarRepository.save(c);
    }

    /**
     * Vincula apoderados con adultos mayores (ownership). Idempotente: solo
     * corre si la tabla está vacía. El primer apoderado queda vinculado a todos
     * los adultos mayores; los demás, al primero — así cada uno ve algo distinto.
     */
    private void seedGuardianElderlyLinks() {
        if (guardianElderlyRepository.count() > 0) return;

        java.util.List<Guardian> guardians = guardianRepository.findAll();
        java.util.List<Elderly> elderlyList = elderlyRepository.findAll();
        if (guardians.isEmpty() || elderlyList.isEmpty()) return;

        Guardian first = guardians.get(0);
        for (Elderly e : elderlyList) {
            linkGuardianElderly(first, e);
        }
        for (int i = 1; i < guardians.size(); i++) {
            linkGuardianElderly(guardians.get(i), elderlyList.get(0));
        }
    }

    private void linkGuardianElderly(Guardian guardian, Elderly elderly) {
        GuardianElderly link = new GuardianElderly();
        link.setGuardian(guardian);
        link.setElderly(elderly);
        guardianElderlyRepository.save(link);
    }

    // ── Helpers ───────────────────────────────────────────────────

    private Role role(String name) {
        Role r = new Role();
        r.setRoleName(name);
        return roleRepository.save(r);
    }

    private User user(String username, String email, Role role, String createdAt) {
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode("password123"));
        u.setRole(role);
        u.setEnabled(true);
        u.setCreatedAt(LocalDate.parse(createdAt));
        return userRepository.save(u);
    }

    private Guardian guardian(User user, String phone, String address, String relationship, String dni) {
        Guardian g = new Guardian();
        g.setUser(user);
        g.setPhone(phone);
        g.setAddress(address);
        g.setRelationship(relationship);
        g.setDni(dni);
        return guardianRepository.save(g);
    }

    private Nurse nurse(User user, String licenseNumber) {
        Nurse n = new Nurse();
        n.setUser(user);
        n.setLicenseNumber(licenseNumber);
        return nurseRepository.save(n);
    }

    private Elderly elderly(User user, String dni, String bloodType, String gender,
                            String height, String allergies, String weight,
                            String diseases, String medication, String observations) {
        Elderly e = new Elderly();
        e.setUser(user);
        e.setDni(dni);
        e.setBloodType(bloodType);
        e.setGender(gender);
        e.setHeight(new BigDecimal(height));
        e.setAllergies(allergies);
        e.setCurrentWeight(new BigDecimal(weight));
        e.setChronicDiseases(diseases);
        e.setCurrentMedication(medication);
        e.setMedicalObservations(observations);
        return elderlyRepository.save(e);
    }

    private void healthCenter(String name, String lat, String lon,
                              String phone, String address, String rating) {
        HealthCenter h = new HealthCenter();
        h.setCenterName(name);
        h.setLatitude(new BigDecimal(lat));
        h.setLongitude(new BigDecimal(lon));
        h.setEmergencyPhone(phone);
        h.setAddress(address);
        h.setRating(new BigDecimal(rating));
        healthCenterRepository.save(h);
    }

    private Subscription subscription(Guardian guardian, String planType,
                                      String startDate, String expiryDate,
                                      String status, String price, String discount) {
        Subscription s = new Subscription();
        s.setGuardian(guardian);
        s.setPlanType(planType);
        s.setStartDate(LocalDate.parse(startDate));
        s.setExpiryDate(LocalDate.parse(expiryDate));
        s.setStatus(status);
        s.setPrice(new BigDecimal(price));
        s.setDiscount(new BigDecimal(discount));
        return subscriptionRepository.save(s);
    }

    private void payment(Subscription subscription, String date, String amount, String status) {
        Payment p = new Payment();
        p.setSubscription(subscription);
        p.setPaymentDate(LocalDate.parse(date));
        p.setPaymentTime(LocalTime.of(10, 0));
        p.setAmount(new BigDecimal(amount));
        p.setStatus(status);
        paymentRepository.save(p);
    }

    private void monitoring(Elderly elderly, Nurse nurse, String vitalStatus,
                            String date, String time, String temp,
                            String bp, String observations, String medStatus) {
        Monitoring m = new Monitoring();
        m.setElderly(elderly);
        m.setNurse(nurse);
        m.setVitalSignsStatus(vitalStatus);
        m.setMonitoringDate(LocalDate.parse(date));
        m.setMonitoringTime(LocalTime.parse(time));
        m.setTemperature(new BigDecimal(temp));
        m.setBloodPressure(bp);
        m.setObservations(observations);
        m.setMedicineStatus(medStatus);
        monitoringRepository.save(m);
    }

    private void calendar(String appointmentDate, String appointmentTime,
                          String medicineDate, String medicineTime,
                          String therapyDate, String therapyTime,
                          String vaccines) {
        Calendar c = new Calendar();
        c.setAppointmentDate(LocalDate.parse(appointmentDate));
        c.setAppointmentTime(LocalTime.parse(appointmentTime));
        c.setMedicineDate(LocalDate.parse(medicineDate));
        c.setMedicineTime(LocalTime.parse(medicineTime));
        c.setTherapyDate(LocalDate.parse(therapyDate));
        c.setTherapyTime(LocalTime.parse(therapyTime));
        c.setVaccines(vaccines);
        calendarRepository.save(c);
    }
}
