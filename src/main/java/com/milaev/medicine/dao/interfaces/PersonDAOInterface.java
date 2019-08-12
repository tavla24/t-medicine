package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Person;

import java.util.List;

public interface PersonDAOInterface<T extends Person> {

    List<T> getAll();

    T getById(Long id);

    T getByLogin(String login);

    T getByFullName(String fname, String surname, String patronymic, String specify);

    void insert(T acc);

    void delete(T acc);

    void update(T acc);
}
