package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.AccountDAOInterface;
import com.milaev.medicine.model.Account;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public void insert(Account acc) {
        per(acc);
    }

    @Override
    public void delete(Account acc) {
        del(acc);
    }

    @Override
    public void update(Account acc) {
        upd(acc);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Account getByLogin(String login) {
        Query<Account> query = getCurrentSession().createQuery("from Account where login = :param1");
        return getByParamsSingle(query, login);
    }
}
