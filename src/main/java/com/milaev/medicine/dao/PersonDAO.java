package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.PersonDAOInterface;
import com.milaev.medicine.model.Person;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PersonDAO extends AbstractDAO<Person> implements PersonDAOInterface {

    private static Logger log = LoggerFactory.getLogger(PersonDAO.class);

    @Override
    public List<Person> getByPhone(String phone) {
        Query<Person> query = getCurrentSession().createQuery("from Person as p where p.phone = :param1");
        return getByParams(query, phone);
    }

    @Override
    public List<Person> getByEmail(String email) {
        Query<Person> query = getCurrentSession().createQuery("from Person as p where p.email = :param1");
        return getByParams(query, email);
    }
}
