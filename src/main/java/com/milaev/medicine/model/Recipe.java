package com.milaev.medicine.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false)
    private long id;
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(nullable = false)
    private Date periodic;
    @Column(nullable = false)
    private Date duration;
    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "healing_id", nullable = false)
    private Healing healing;
    @Column
    private float dose;
    
    // TODO periodic + duration - ??

    public Recipe() {
    }

    public Recipe(long id, Patient patient, Date periodic, Date duration, Healing healing, float dose) {
        super();
        this.id = id;
        this.patient = patient;
        this.periodic = periodic;
        this.duration = duration;
        this.healing = healing;
        this.dose = dose;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getPeriodic() {
        return periodic;
    }

    public void setPeriodic(Date periodic) {
        this.periodic = periodic;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public Healing getHealing() {
        return healing;
    }

    public void setHealing(Healing healing) {
        this.healing = healing;
    }

    public float getDose() {
        return dose;
    }

    public void setDose(float dose) {
        this.dose = dose;
    }

}
