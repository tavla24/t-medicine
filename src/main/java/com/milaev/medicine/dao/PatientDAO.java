package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.PatientDAOInterface;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.Patient;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PatientDAO implements PatientDAOInterface {

    private static Logger log = LoggerFactory.getLogger(PatientDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Patient> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Patient").list();
    }

    @Override
    public List<Patient> getByDoctorLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<Patient> query = session.createQuery("from Patient as p where p.doctor.account.login = :paramName");
        query.setParameter("paramName", login);
        return query.list();
    }

    @Override
    public Patient getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query<Patient> query = session.createQuery("from Patient as p where p.account.login = :paramName");
        query.setParameter("paramName", login);
        return getSingleResult(query);
    }

    @Override
    public Patient getByInsuranceID(String insuranceID) {
        return null;
    }

    @Override
    public Patient getByFullName(String fname, String surname, String patronymic, String specify) {
        return null;
    }

    @Override
    public Patient getById(int id) {
        return null;
    }

    @Override
    public boolean add(Patient acc) {
        return false;
    }

    @Override
    public boolean delete(Patient acc) {
        return false;
    }

    @Override
    public boolean edit(Patient acc) {
        return false;
    }

    public static Patient getSingleResult(Query<Patient> query) {
        query.setMaxResults(1);
        List<Patient> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
