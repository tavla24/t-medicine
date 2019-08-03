package com.milaev.medicine.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Column(name = "specialization", nullable = false)
    private String specialization;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY)
    private Collection<Patient> patients;

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getLogin() {
        if (this.getAccount() == null)
            this.setAccount(new Account());
        return getAccount().getLogin();
    }

    public void setLogin(String login) {
        if (this.getAccount() == null)
            this.setAccount(new Account());
        getAccount().setLogin(login);
    }

    public Collection<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Collection<Patient> patients) {
        this.patients = patients;
    }
}
