package com.milaev.medicine.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.EventDAOInterface;
import com.milaev.medicine.model.DayPart;
import com.milaev.medicine.model.Event;

@Repository
public class EventDAO extends AbstractDAO<Event> implements EventDAOInterface {
    
    private static Logger log = LoggerFactory.getLogger(EventDAO.class);

    @Override
    public List<Event> getByRecipeId(Long id) {
        Query<Event> query = getCurrentSession().createQuery("from Event as f where f.recipe.id = :param1");
        return getByParams(query, id.toString());
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
    public boolean insert(Event acc) {
        return per(acc);
    }

    @Override
    public boolean delete(Event acc) {
        return del(acc);
    }

    @Override
    public boolean update(Event acc) {
        return upd(acc);
    }

}
