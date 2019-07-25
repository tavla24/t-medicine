package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.AccountDAOInterface;
import com.milaev.medicine.model.Account;

@Repository
public class AccountDAO implements AccountDAOInterface {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Account> allAccounts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Account").list();
    }

    @Override
    public void add(Account acc) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(acc);
    }

    @Override
    public void delete(Account acc) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(acc);
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

    @Override
    public Account getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, login);
    }

}
