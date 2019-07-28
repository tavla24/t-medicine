package com.milaev.medicine.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.dao.interfaces.PersistentTokenDAOInterface;
import com.milaev.medicine.model.PersistentToken;
import com.milaev.medicine.service.interfaces.TPersistentTokenServiceInterface;

@Service
public class PersistentTokenService implements TPersistentTokenServiceInterface {

    @Autowired
    @Qualifier("persistentTokenDAO")
    private PersistentTokenDAOInterface dao;

    @Override
    @Transactional
    public PersistentToken getByKey(String key) {
        return dao.getByKey(key);
    }

    @Override
    @Transactional
    public void persist(PersistentToken entity) {
        dao.persist(entity);
    }

    @Override
    @Transactional
    public void update(PersistentToken entity) {
        dao.update(entity);
    }

    @Override
    @Transactional
    public void delete(PersistentToken entity) {
        dao.delete(entity);
    }

    @Override
    @Transactional
    public void createNewToken(PersistentRememberMeToken token) {
        dao.createNewToken(token);
    }

    @Override
    @Transactional
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        dao.updateToken(series, tokenValue, lastUsed);
    }

    @Override
    @Transactional
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return dao.getTokenForSeries(seriesId);
    }

    @Override
    @Transactional
    public void removeUserTokens(String username) {
        dao.removeUserTokens(username);
    }

}
