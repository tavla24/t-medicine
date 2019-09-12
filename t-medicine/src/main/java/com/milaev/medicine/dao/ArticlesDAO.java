package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.ArticlesDAOInterface;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Article;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ArticlesDAO extends AbstractDAO<Article> implements ArticlesDAOInterface {

    @Override
    public List<Article> getAll(){
        Query<Article> query = getCurrentSession().createQuery("from Article");
        return getAll(query);
    }
}
