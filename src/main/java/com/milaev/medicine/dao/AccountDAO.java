package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
//        Account accExist = getByLogin(acc.getLogin());
//        accExist.setLogin(acc.getLogin());
//        accExist.setPassword(acc.getPassword());
//        accExist.setRole(acc.getRole());
//        accExist.setRoleID(acc.getRoleID());

        Session session = sessionFactory.getCurrentSession();
        session.update(acc);
    }

    @Override
    public Account getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Account.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Account getByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        // TODO by criteriabuilder
        // Criteria criteria = session.createCriteria(Account.class);
        Query<Account> query = session.createQuery("from Account where login = :paramName");
        query.setParameter("paramName", login);
        // TODO unique?
        return query.getSingleResult();// list();

        // List<Account> list = query.list();
        // if (list == null || list.isEmpty()) {
        // return null;
        // }
        // return list.get(0);
    }
}
