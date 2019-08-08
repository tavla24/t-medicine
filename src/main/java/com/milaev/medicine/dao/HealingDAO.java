package com.milaev.medicine.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.HealingDAOInterface;
import com.milaev.medicine.model.Event;
import com.milaev.medicine.model.Healing;

@Repository
public class HealingDAO extends AbstractDAO<Healing> implements HealingDAOInterface {
    
    private static Logger log = LoggerFactory.getLogger(HealingDAO.class);

    @Override
    public List<Healing> getByRecipeId(Long id) {
        Query<Healing> query = getCurrentSession().createQuery("from Healing as f where f.recipe.id = :param1");
        return getByParams(query, id.toString());
    }

    @Override
    public void insert(Healing acc) {
        per(acc);
    }

    @Override
    public void delete(Healing acc) {
        del(acc);
    }

    @Override
    public void update(Healing acc) {
        upd(acc);
    }

}
