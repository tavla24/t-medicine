package com.milaev.medicine.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes_simple")
public class RecipeSimple {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "healing_name", nullable = false)
    private String healingName;

    @Column(name = "healing_type", nullable = false)
    private String healingType;

    @Column(name = "healthful", nullable = false)
    private boolean healthful;

    @Column(name = "doze", nullable = false)
    private String doze;

    @Column(name = "day_names", nullable = false)
    private String dayNames;

    @Column(name = "day_parts", nullable = false)
    private String dayParts;

    @Column(name = "date_from", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateFrom;

    @Column(name = "date_to", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dateTo;

    @OneToMany(mappedBy = "recipe", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    private List<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getHealingName() {
        return healingName;
    }

    public void setHealingName(String healingName) {
        this.healingName = healingName;
    }

    public String getHealingType() {
        return healingType;
    }

    public void setHealingType(String healingType) {
        this.healingType = healingType;
    }

    public boolean isHealthful() {
        return healthful;
    }

    public void setHealthful(boolean healthful) {
        this.healthful = healthful;
    }

    public String getDoze() {
        return doze;
    }

    public void setDoze(String doze) {
        this.doze = doze;
    }

    public String getDayNames() {
        return dayNames;
    }

    public void setDayNames(String dayNames) {
        this.dayNames = dayNames;
    }

    public String getDayParts() {
        return dayParts;
    }

    public void setDayParts(String dayParts) {
        this.dayParts = dayParts;
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

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
