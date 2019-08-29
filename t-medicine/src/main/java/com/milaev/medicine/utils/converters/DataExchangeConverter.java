package com.milaev.medicine.utils.converters;

import com.milaev.medicine.model.Event;
import com.milaev.mq.data.ExchangeData;

public class DataExchangeConverter {

    public static ExchangeData toDTO(Event db) {
        ExchangeData ed = new ExchangeData();

        ed.setId(db.getId().toString());

        ed.setDatestamp(db.getDatestamp());
        ed.setStatus(db.getStatus());
        ed.setInfo(db.getInfo());

        ed.setHealingName(db.getRecipe().getHealingName());
        ed.setHealingType(db.getRecipe().getHealingType());
        ed.setDoze(db.getRecipe().getDoze());
        ed.setHealthful(db.getRecipe().isHealthful());

        ed.setNamePatient(String.format("%s %s %s", db.getRecipe().getPatient().getSurname(), db.getRecipe().getPatient().getName(), db.getRecipe().getPatient().getPatronymic()));
        ed.setEmailPatient(db.getRecipe().getPatient().getEmail());
        ed.setPhonePatient(db.getRecipe().getPatient().getPhone());
        ed.setInsuranceId(db.getRecipe().getPatient().getInsuranceId());
        ed.setDiagnosis(db.getRecipe().getPatient().getDiagnosis());
        ed.setStatusPatient(db.getRecipe().getPatient().getStatus());

        ed.setNameDoctor(String.format("%s %s %s", db.getRecipe().getPatient().getDoctor().getSurname(), db.getRecipe().getPatient().getDoctor().getName(), db.getRecipe().getPatient().getDoctor().getPatronymic()));
        ed.setEmailDoctor(db.getRecipe().getPatient().getDoctor().getEmail());
        ed.setPhoneDoctor(db.getRecipe().getPatient().getDoctor().getPhone());
        ed.setSpecialization(db.getRecipe().getPatient().getDoctor().getSpecialization());

        return ed;
    }
}
