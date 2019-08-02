package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.DoctorDAOInterface;
import com.milaev.medicine.model.Doctor;

@Repository
public class DoctorDAO implements DoctorDAOInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Doctor> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Doctor").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Doctor getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<Doctor> query = session.createQuery("from Doctor D where D.account.login = :paramName");
        query.setParameter("paramName", login);
        return query.getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Doctor getByFullName(String fname, String surname, String patronymic, String specify) {
        // TODO specify
        Session session = sessionFactory.getCurrentSession();
        Query<Doctor> query = session
                .createQuery("from Doctor D where D.name = :param1 and D.surname = :param2 and D.patronymic = :param3");
        query.setParameter("param1", fname);
        query.setParameter("param2", surname);
        query.setParameter("param3", patronymic);
        return query.getSingleResult();
    }

    @Override
    public Doctor getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Doctor.class, id);
    }

    @Override
    public boolean add(Doctor acc) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.persist(acc);
        } catch (Exception ex) {
            // TODO exceptions (double var) dont catched
            log.error("");
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Doctor acc) {
        Session session = sessionFactory.getCurrentSession();
        try {
            session.delete(acc);
        } catch (Exception ex) {
            log.error("");
            return false;
        }
        return true;
    }

    @Override
    public boolean edit(Doctor acc) {
        // TODO Auto-generated method stub
        return false;
    }

}
