package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Doctor;

public interface DoctorDAOInterface {

    List<Doctor> getAll();

    Doctor getByLogin(String login);

    Doctor getByFullName(String fname, String surname, String patronymic, String specify);

    Doctor getById(int id);

    boolean insert(Doctor acc);

    boolean delete(Doctor acc);

    boolean update(Doctor acc);
}
