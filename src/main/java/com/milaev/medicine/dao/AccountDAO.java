package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.AccountDAOInterface;
import com.milaev.medicine.model.Account;

@Repository
public class AccountDAO extends AbstractDAO<Account> implements AccountDAOInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorDAO.class);

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> getAll() {
        Query<Account> query = getCurrentSession().createQuery("from Account");
        return getAll(query);
    }

    @Override
    public boolean insert(Account acc) {
        return per(acc);
    }

    @Override
    public boolean delete(Account acc) {
        return del(acc);
    }

    @Override
    public boolean update(Account acc) {
        return upd(acc);
    }

//    @Override
//    public Account getById(int id) {
//        return getById(id);
//    }

    @Override
    @SuppressWarnings("unchecked")
    public Account getByLogin(String login) {
        Query<Account> query = getCurrentSession().createQuery("from Account where login = :param1");
        return getByParamsSingle(query, login);
    }
}
