package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.RecipeDAOInterface;
import com.milaev.medicine.model.Recipe;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecipeDAO extends AbstractDAO<Recipe> implements RecipeDAOInterface {

    private static Logger log = LoggerFactory.getLogger(RecipeDAO.class);

    @Override
    public List<Recipe> getAll() {
        Query<Recipe> query = getCurrentSession().createQuery("from Recipe");
        return getAll(query);
//        Query<Recipe> query = sessionFactory.getCurrentSession().createQuery("from Recipe");
//        return query.getResultList();
    }

    @Override
    public Recipe getByInsuranceId(String insuranceId) {
        Query<Recipe> query = getCurrentSession()
                .createQuery("from Recipe as p where p.patient.insuranceId = :param1");
        return getByParamsSingle(query, insuranceId);
    }

    @Override
    public void insert(Recipe acc) {
        per(acc);
    }

    @Override
    public void delete(Recipe acc) {
        del(acc);
    }

    @Override
    public void update(Recipe acc) {
        upd(acc);
    }
}
