package com.milaev.medicine.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.db.dao.interfaces.AccountDAOInterface;
import com.milaev.medicine.mvc.model.accounts.Account;

@Repository
public class AccountDAO implements AccountDAOInterface {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Account> allAccounts() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void add(Account acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Account acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public void edit(Account acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public Account getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

}