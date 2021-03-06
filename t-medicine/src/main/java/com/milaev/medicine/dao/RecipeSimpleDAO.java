package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.RecipeSimpleDAOInterface;
import com.milaev.medicine.model.RecipeSimple;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeSimpleDAO extends AbstractDAO<RecipeSimple> implements RecipeSimpleDAOInterface {

    private static Logger log = LoggerFactory.getLogger(RecipeSimpleDAO.class);

    @Override
    public List<RecipeSimple> getAll() {
        Query<RecipeSimple> query = getCurrentSession().createQuery("from RecipeSimple");
        return getAll(query);
    }

    @Override
    public List<RecipeSimple> getByInsuranceId(String insuranceId) {
        Query<RecipeSimple> query = getCurrentSession()
                .createQuery("from RecipeSimple as p where p.patient.insuranceId = :param1");
        return getByParams(query, insuranceId);
    }

    @Override
    public RecipeSimple insert(RecipeSimple acc) {
        return per(acc);
    }

    @Override
    public RecipeSimple delete(RecipeSimple acc) {
        return del(acc);
    }

    @Override
    public RecipeSimple update(RecipeSimple acc) {
        return upd(acc);
    }
}
