package com.milaev.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "doctors")
public class Doctor extends Person {

    @Column(name = "specialization", nullable = false)
    private String specialization;

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
}
