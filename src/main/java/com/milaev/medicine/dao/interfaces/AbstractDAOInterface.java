package com.milaev.medicine.dao.interfaces;

import org.hibernate.Session;

import javax.persistence.TypedQuery;
import java.util.List;

public interface AbstractDAOInterface<T> {

    List<T> getAll();

    T getById(int id);

    T getByLogin(String login);

    T getByFullName(String fname, String surname, String patronymic, String specify);

    boolean insert(T acc);

    boolean delete(T acc);

    boolean update(T acc);
}
