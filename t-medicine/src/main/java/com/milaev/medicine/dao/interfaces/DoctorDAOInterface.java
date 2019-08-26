package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Doctor;

import java.util.List;

public interface DoctorDAOInterface {
    List<Doctor> getAll();

    Doctor getById(Long id);

    Doctor getByLogin(String login);

    void insert(Doctor acc);

    void delete(Doctor acc);

    void update(Doctor acc);
}
