package com.tayta.Entities;

import jakarta.persistence.*;

@Entity
public class NurseCalendars {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_nurseCalendar")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_calendar")
    private Calendar calendar;

    @ManyToOne
    @JoinColumn(name = "id_nurse")
    private Nurse nurse;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }
}
