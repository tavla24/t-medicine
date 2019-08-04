package com.milaev.medicine.dto;

import com.milaev.medicine.model.Healing;
import com.milaev.medicine.model.Patient;

import java.util.Date;

public class RecipeDTO {
    private PatientDTO patient;
    private HealingDTO healing;
    private Date dateFrom;
    private Date dateTo;

    public PatientDTO getPatient() {
        if (patient == null)
            patient = new PatientDTO();
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public HealingDTO getHealing() {
        if (healing == null)
            healing = new HealingDTO();
        return healing;
    }

    public void setHealing(HealingDTO healing) {
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
}
