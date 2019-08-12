package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.EventDAOInterface;
import com.milaev.medicine.model.Event;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class EventDAO extends AbstractDAO<Event> implements EventDAOInterface {

    private static Logger log = LoggerFactory.getLogger(EventDAO.class);

    @Override
    public List<Event> getAll() {
        Query<Event> query = getCurrentSession().createQuery("from Event");
        return getAll(query);
    }

    @Override
    public List<Event> getByFilter(String queryString, Map<String, Object> queryParams) {
        return getByQuery(queryString, queryParams);
    }

    @Override
    public List<Event> getByRecipeId(Long id) {
        Query<Event> query = getCurrentSession().createQuery("from Event as f where f.recipe.id = :param1");
        return getByParams(query, id);
    }

    @Override
    public List<Event> getByInsuranceId(String insuranceId) {
        Query<Event> query = getCurrentSession().createQuery("from Event as f where f.recipe.patient.insuranceId = :param1");
        return getByParams(query, insuranceId);
    }

    @Override
    public List<Event> getRecipesByTime() {
        Query<Event> query = getCurrentSession().createQuery("from Event as f order by f.date");
        return getQResults(query);
    }

    @Override
    public List<Event> getRecipesByTime(Date date) {
        Query<Event> query = getCurrentSession().createQuery("from Event as f where f.Date > :param1 order by f.date");
        query.setParameter("param1", date);
        return getQResults(query);
    }

    @Override
    public List<Event> getRecipesByTime(Date dateFrom, Date dateTo) {
        Query<Event> query = getCurrentSession().createQuery("from Event as f where f.Date > :param1 and f.Date < :param2 order by f.date");
        query.setParameter("param1", dateFrom);
        query.setParameter("param1", dateTo);
        return getQResults(query);
    }

    @Override
    public void insert(Event acc) {
        per(acc);
    }

    @Override
    public void delete(Event acc) {
        del(acc);
    }

    @Override
    public void update(Event acc) {
        upd(acc);
    }

}
