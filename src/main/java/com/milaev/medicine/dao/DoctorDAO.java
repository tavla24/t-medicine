package com.milaev.medicine.dao;

import java.util.List;

import com.milaev.medicine.dao.interfaces.AbstractDAOInterface;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.DoctorDAOInterface;
import com.milaev.medicine.model.Doctor;

@Repository
public class DoctorDAO extends AbstractDAO<Doctor> implements DoctorDAOInterface, AbstractDAOInterface<Doctor> {

    private static Logger log = LoggerFactory.getLogger(DoctorDAO.class);

    @Override
    @SuppressWarnings("unchecked")
    public List<Doctor> getAll() {
        Query<Doctor> query = getCurrentSession().createQuery("from Doctor");
        //Query<Doctor> query = getCurrentSession().createQuery("from Doctor as d where d.account.role.type = :paramName");
        //query.setParameter("paramName", "DOCTOR");

        return getAll(query);//getByParams(query);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Doctor getByLogin(String login) {
        Query<Doctor> query = getCurrentSession().createQuery("from Doctor as d where d.account.login = :param1");
        return getByParamsSingle(query, login);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Doctor getByFullName(String fname, String surname, String patronymic, String specify) {
        // TODO specify
        Query<Doctor> query = getCurrentSession()
                .createQuery("from Doctor D where D.name = :param1 and D.surname = :param2 and D.patronymic = :param3");
        // TODO if some equals persons
        return getByParamsSingle(query, fname, surname, patronymic);
    }

    @Override
    public Doctor getById(int id) {
        return getById(id);
    }

    @Override
    public boolean insert(Doctor acc) {
        return per(acc);
    }

    @Override
    public boolean delete(Doctor acc) {
        return del(acc);
    }

    @Override
    public boolean update(Doctor acc) {
        return upd(acc);
    }

}
