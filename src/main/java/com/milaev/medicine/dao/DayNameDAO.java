package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.DayNameDAOInterface;
import com.milaev.medicine.model.DayName;

@Repository
public class DayNameDAO extends AbstractDAO<DayName> implements DayNameDAOInterface{
    
    private static Logger log = LoggerFactory.getLogger(DayNameDAO.class);

    @Override
    public List<DayName> getByRecipeId(Long id) {
        Query<DayName> query = getCurrentSession().createQuery("from DayName as f where f.recipe.id = :param1");
        return getByParams(query, id);
    }

    @Override
    public boolean insert(DayName acc) {
        return per(acc);
    }

    @Override
    public boolean delete(DayName acc) {
        return del(acc);
    }

    @Override
    public boolean update(DayName acc) {
        return upd(acc);
    }

}
