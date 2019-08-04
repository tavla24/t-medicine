package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Person;
import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public interface PersonDAOInterface<T extends Person> {

    List<T> getAll();

    T getById(int id);

    T getByLogin(String login);

    T getByFullName(String fname, String surname, String patronymic, String specify);

    boolean insert(T acc);

    boolean delete(T acc);

    boolean update(T acc);
}