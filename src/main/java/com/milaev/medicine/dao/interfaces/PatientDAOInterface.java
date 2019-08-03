package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Patient;

import java.util.List;

public interface PatientDAOInterface {
    List<Patient> getAll();

    List<Patient> getByDoctorLogin(String login);

    Patient getByLogin(String login);

    Patient getByInsuranceID(String insuranceID);

    Patient getByFullName(String fname, String surname, String patronymic, String specify);

    Patient getById(int id);

    boolean add(Patient acc);

    boolean delete(Patient acc);

    boolean edit(Patient acc);
}
