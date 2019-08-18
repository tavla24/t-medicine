package com.milaev.medicine.service;

import com.milaev.medicine.dao.PersonDAO;
import com.milaev.medicine.service.interfaces.PersonServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonService implements PersonServiceInterface {

    @Autowired
    private PersonDAO daoPerson;

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    @Override
    public boolean isEmailUnique(String email) {
        return (daoPerson.getByEmail(email).isEmpty());
    }

    @Override
    public boolean isPhoneUnique(String phone) {
        return (daoPerson.getByPhone(phone).isEmpty());
    }
}
