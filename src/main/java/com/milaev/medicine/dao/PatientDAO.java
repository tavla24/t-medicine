package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.PatientDAOInterface;
import com.milaev.medicine.model.Patient;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PatientDAO extends AbstractDAO<Patient> implements PatientDAOInterface {

    private static Logger log = LoggerFactory.getLogger(PatientDAO.class);

    @Override
    public Patient getById(int id) {
        return getById(id);
    }

    @Override
    public List<Patient> getAll() {
        Query<Patient> query = getCurrentSession().createQuery("from Patient");
        return getAll(query);
    }

    @Override
    public List<Patient> getByDoctorLogin(String login) {
        Query<Patient> query = getCurrentSession().createQuery("from Patient as p where p.doctor.account.login = :param1");
        return getByParams(query, login);
    }

    @Override
    public Patient getByLogin(String login) {
        Query<Patient> query = getCurrentSession().createQuery("from Patient as p where p.account.login = :param1");
        return getByParamsSingle(query, login);
    }

    @Override
    public Patient getByInsuranceID(String insuranceId) {
        Query<Patient> query = getCurrentSession()
                .createQuery("from Patient as p where p.insuranceId = :param1");
        return getByParamsSingle(query, insuranceId);
    }

    @Override
    public Patient getByFullName(String fname, String surname, String patronymic, String specify) {
        Query<Patient> query = getCurrentSession()
                .createQuery("from Patient as p where p.name = :param1 and p.surname = :param2 and p.patronymic = :param3");
        return getByParamsSingle(query, fname, surname, patronymic);
    }

    @Override
    public boolean insert(Patient acc) {
        return per(acc);
    }

    @Override
    public boolean delete(Patient acc) {
        return del(acc);
    }

    @Override
    public boolean update(Patient acc) {
        return upd(acc);
    }
}
