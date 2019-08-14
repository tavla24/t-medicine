package com.milaev.medicine.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    private List<Patient> patients;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }
}
