package com.milaev.medicine.model;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @OneToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "healing_id", nullable = false)
    private Healing healing;

    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<RecipeDayNames> dayNames;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY)
    private List<Event> events;

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

    public Healing getHealing() {
        return healing;
    }

    public void setHealing(Healing healing) {
        this.healing = healing;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public List<RecipeDayNames> getDayNames() {
        return dayNames;
    }

    public void setDayNames(List<RecipeDayNames> dayNames) {
        this.dayNames = dayNames;
    }
}
