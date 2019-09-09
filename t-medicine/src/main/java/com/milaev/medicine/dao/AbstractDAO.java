package com.milaev.medicine.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    public T getById(Long id) {
        return getCurrentSession().get(persistentClass, id);
    }

    protected List<T> getAll(TypedQuery<T> query) {
        return query.getResultList();
    }

    protected Long getCountByQuery(String queryString, Map<String, Object> queryParams) {
        Query<T> query = getCurrentSession().createQuery(queryString);

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return (Long)query.uniqueResult();
    }

    protected List<T> getByQuery(String queryString, Map<String, Object> queryParams) {
        Query<T> query = getCurrentSession().createQuery(queryString);

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return getQResults(query);
        //return query.getQResults();
    }

    protected List<T> getByQuery(String queryString, Map<String, Object> queryParams, int start, int size) {
        Query<T> query = getCurrentSession().createQuery(queryString);

        query.setFirstResult(start);
        query.setMaxResults(size);

        for (Map.Entry<String, Object> entry : queryParams.entrySet()) {
            query.setParameter(entry.getKey(), entry.getValue());
        }

        return getQResults(query);
        //return query.getQResults();
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

    protected List<T> getByParams(TypedQuery<T> query, Long... params) {
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.format("param%d", i + 1), params[i]);
        }
        return getQResults(query);
        //return query.getQResults();
    }

    protected T getByParamsSingle(TypedQuery<T> query, Long... params) {
        for (int i = 0; i < params.length; i++) {
            query.setParameter(String.format("param%d", i + 1), params[i]);
        }
        return getQResult(query);
        //return query.getQResult();
    }

    protected T per(T db) {
        try {
            getCurrentSession().persist(db);
        } catch (Exception ex) {
            // TODO exceptions (double var) dont catched
            log.error("Exception /persist/ from DAO during DB query");
            ex.printStackTrace();
        }
        return db;
    }

    protected T del(T db) {
        try {
            getCurrentSession().delete(db);
        } catch (Exception ex) {
            log.error("Exception /delete/ from DAO during DB query");
            ex.printStackTrace();
        }
        return db;
    }

    protected T upd(T db) {
        try {
            getCurrentSession().update(db);
        } catch (Exception ex) {
            log.error("Exception /update/ from DAO during DB query");
            ex.printStackTrace();
        }
        return db;
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
//            return null;
            return new ArrayList<T>();
        }

        return list;
    }

}
