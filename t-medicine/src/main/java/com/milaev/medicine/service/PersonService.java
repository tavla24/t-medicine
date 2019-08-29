package com.milaev.medicine.service;

import com.milaev.medicine.dao.PersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonService {

    @Autowired
    private PersonDAO daoPerson;

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    public boolean isEmailUnique(String email) {
        return (daoPerson.getByEmail(email).isEmpty());
    }

    public boolean isPhoneUnique(String phone) {
        return (daoPerson.getByPhone(phone).isEmpty());
    }
}
