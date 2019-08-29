package com.milaev.medicine.service;

import com.milaev.medicine.dao.interfaces.PersistentTokenDAOInterface;
import com.milaev.medicine.model.PersistentToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PersistentTokenService implements PersistentTokenRepository {

    @Autowired
    @Qualifier("persistentTokenDAO")
    private PersistentTokenDAOInterface dao;

    @Transactional
    public PersistentToken getByKey(String key) {
        return dao.getByKey(key);
    }

    @Transactional
    public void persist(PersistentToken entity) {
        dao.persist(entity);
    }

    @Transactional
    public void update(PersistentToken entity) {
        dao.update(entity);
    }

    @Transactional
    public void delete(PersistentToken entity) {
        dao.delete(entity);
    }

    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        dao.createNewToken(token);
    }

    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        dao.updateToken(series, tokenValue, lastUsed);
    }

    @Transactional
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return dao.getTokenForSeries(seriesId);
    }

    @Transactional
    public void removeUserTokens(String username) {
        dao.removeUserTokens(username);
    }

}
