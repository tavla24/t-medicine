package com.milaev.medicine.db.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.db.dao.interfaces.AccountSimpleDAOInterface;
import com.milaev.medicine.db.entity.AccountSimple;

@Repository
public class AccountSimpleDAO implements AccountSimpleDAOInterface {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<AccountSimple> allAccounts() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from AccountSimple").list();
    }

    @Override
    public void add(AccountSimple acc) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(acc);
    }

    @Override
    public void delete(AccountSimple acc) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(acc);
    }

    @Override
    public void edit(AccountSimple acc) {
        // TODO Auto-generated method stub

    }

    @Override
    public AccountSimple getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(AccountSimple.class, id);
    }

}
