package com.tayta.Entities;

import jakarta.persistence.*;

@Entity
public class GuardianElderly {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_guardianElderly")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_guardian")
    private Guardian guardian;

    @ManyToOne
    @JoinColumn(name = "id_elderly")
    private Elderly elderly;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public Elderly getElderly() {
        return elderly;
    }

    public void setElderly(Elderly elderly) {
        this.elderly = elderly;
    }
}
