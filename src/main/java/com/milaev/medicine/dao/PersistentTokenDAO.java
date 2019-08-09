package com.milaev.medicine.dao;

import java.util.Date;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Repository;

import com.milaev.medicine.dao.interfaces.PersistentTokenDAOInterface;
import com.milaev.medicine.model.PersistentToken;

@Repository
public class PersistentTokenDAO implements PersistentTokenDAOInterface {

    private static Logger log = LoggerFactory.getLogger(PersistentTokenDAO.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        log.info("Creating Token for user : " + token.getUsername());
        PersistentToken persistentLogin = new PersistentToken();
        persistentLogin.setUsername(token.getUsername());
        persistentLogin.setSeries(token.getSeries());
        persistentLogin.setToken(token.getTokenValue());
        persistentLogin.setLast_used(token.getDate());
        persist(persistentLogin);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        log.info("Fetch Token if any for seriesId : " + seriesId);
        try {
            Criteria crit = createEntityCriteria();
            crit.add(Restrictions.eq("series", seriesId));
            PersistentToken persistentLogin = (PersistentToken) crit.uniqueResult();

            return new PersistentRememberMeToken(persistentLogin.getUsername(), persistentLogin.getSeries(),
                    persistentLogin.getToken(), persistentLogin.getLast_used());
        } catch (Exception e) {
            log.info("Token not found...");
            return null;
        }
    }

    @Override
    public void removeUserTokens(String username) {
        log.info("Removing Token if any for user : " + username);
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("username", username));
        PersistentToken persistentLogin = (PersistentToken) crit.uniqueResult();
        if (persistentLogin != null) {
            log.info("rememberMe was selected");
            delete(persistentLogin);
        }

    }

    @Override
    public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
        log.info("Updating Token for seriesId : " + seriesId);
        PersistentToken persistentLogin = getByKey(seriesId);
        persistentLogin.setToken(tokenValue);
        persistentLogin.setLast_used(lastUsed);
        update(persistentLogin);
    }

    @Override
    public PersistentToken getByKey(String key) {
        return sessionFactory.getCurrentSession().get(PersistentToken.class, key);
    }

    @Override
    public void persist(PersistentToken entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void update(PersistentToken entity) {
        sessionFactory.getCurrentSession().update(entity);
    }

    @Override
    public void delete(PersistentToken entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    // TODO deprecated
    protected Criteria createEntityCriteria() {
        return sessionFactory.getCurrentSession().createCriteria(PersistentToken.class);
    }
}
