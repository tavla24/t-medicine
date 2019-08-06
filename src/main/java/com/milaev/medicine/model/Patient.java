package com.milaev.medicine.model;

import javax.persistence.*;

import com.milaev.medicine.model.enums.PatientStatus;

@Entity
@Table(name = "patients")
public class Patient extends Person {
    @Column(nullable = false)
    private String insuranceId;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String status;// = PatientStatus.ILL.getPatientStatus();

    @ManyToOne(fetch = FetchType.LAZY)//, cascade = { CascadeType.MERGE, CascadeType.PERSIST }
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @OneToOne(mappedBy = "patient", fetch = FetchType.LAZY)
    private Recipe recipe;

    public String getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(String insuranceId) {
        this.insuranceId = insuranceId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
