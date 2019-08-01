package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.RoleDAOInterface;
import com.milaev.medicine.model.Role;

@Repository
public class RoleDAO implements RoleDAOInterface {

    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from Role").list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Role getByType(String type) {
        Session session = sessionFactory.getCurrentSession();
        Query<Role> query = session.createQuery("from Role where type = :paramName");
        query.setParameter("paramName", type);
        return query.getSingleResult();
    }

    @Override
    public Role getById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Role.class, id);
    }

}
