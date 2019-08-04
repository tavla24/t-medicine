package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Patient;

import java.util.List;

public interface PatientDAOInterface {
    List<Patient> getAll();

    List<Patient> getByDoctorLogin(String login);

    Patient getByLogin(String login);

    Patient getByInsuranceID(String insuranceId);

    Patient getByFullName(String fname, String surname, String patronymic, String specify);

    Patient getById(int id);

    boolean insert(Patient acc);

    boolean delete(Patient acc);

    boolean update(Patient acc);
}
