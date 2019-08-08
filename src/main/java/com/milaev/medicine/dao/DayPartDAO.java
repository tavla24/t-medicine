package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.DayPartDAOInterface;
import com.milaev.medicine.model.DayPart;

@Repository
public class DayPartDAO extends AbstractDAO<DayPart> implements DayPartDAOInterface {
    
    private static Logger log = LoggerFactory.getLogger(DayPartDAO.class);

    @Override
    public List<DayPart> getByDayNameId(Long id) {
        Query<DayPart> query = getCurrentSession().createQuery("from DayPart as f where f.dayName.id = :param1");
        return getByParams(query, id);
    }

    @Override
    public void insert(DayPart acc) {
        per(acc);
    }

    @Override
    public void delete(DayPart acc) {
        del(acc);
    }

    @Override
    public void update(DayPart acc) {
        upd(acc);
    }

}
