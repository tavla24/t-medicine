package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Patient;

import java.util.List;

public interface PatientDAOInterface {
    List<Patient> getAll();

    List<Patient> getByDoctorLogin(String login);

    Patient getByLogin(String login);

    Patient getByInsuranceId(String insuranceId);

    Patient getByFullName(String fname, String surname, String patronymic, String specify);

    void insert(Patient acc);

    void delete(Patient acc);

    void update(Patient acc);
}
