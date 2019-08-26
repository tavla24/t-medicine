package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Person;

import java.util.List;

public interface PersonDAOInterface {

    List<Person> getByPhone(String phone);

    List<Person> getByEmail(String email);
}
