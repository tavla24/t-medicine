package com.milaev.medicine.dao;

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
