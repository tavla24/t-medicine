package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.DoctorDAOInterface;
import com.milaev.medicine.model.Doctor;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DoctorDAO extends AbstractDAO<Doctor> implements DoctorDAOInterface {

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
    public void insert(Doctor acc) {
        per(acc);
    }

    @Override
    public void delete(Doctor acc) {
        del(acc);
    }

    @Override
    public void update(Doctor acc) {
        upd(acc);
    }

}
