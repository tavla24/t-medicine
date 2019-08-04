package com.milaev.medicine.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class AbstractDAO<T> {

    private static Logger log = LoggerFactory.getLogger(AbstractDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    protected Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    protected List<T> getAll(TypedQuery<T> query) {
        return query.getResultList();
    }

    protected T getById(int id) {
        return getCurrentSession().get(persistentClass, id);
    }

    protected List<T> getByParams(TypedQuery<T> query, String... params) {
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.format("param%d", i + 1), params[i]);
        }
        return getQResults(query);
        //return query.getQResults();
    }

    protected T getByParamsSingle(TypedQuery<T> query, String... params) {
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.format("param%d", i + 1), params[i]);
        }
        return getQResult(query);
        //return query.getQResult();
    }

    protected boolean per(T acc) {
        try {
            getCurrentSession().persist(acc);
        } catch (Exception ex) {
            // TODO exceptions (double var) dont catched
            log.error("Exception /persist/ from DAO during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean del(T acc) {
        try {
            getCurrentSession().delete(acc);
        } catch (Exception ex) {
            log.error("Exception /delete/ from DAO during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    protected boolean upd(T acc) {
        try {
            getCurrentSession().update(acc);
        } catch (Exception ex) {
            log.error("Exception /update/ from DAO during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    protected static <T> T getQResult(TypedQuery<T> query) {
        query.setMaxResults(1);
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list.get(0);
    }

    protected static <T> List<T> getQResults(TypedQuery<T> query) {
        List<T> list = query.getResultList();
        if (list == null || list.isEmpty()) {
            return null;
        }

        return list;
    }

}
