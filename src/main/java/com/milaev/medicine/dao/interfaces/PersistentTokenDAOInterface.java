package com.milaev.medicine.dao.interfaces;

import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.milaev.medicine.model.PersistentToken;

public interface PersistentTokenDAOInterface extends PersistentTokenRepository{

    public PersistentToken getByKey(String key);

    public void persist(PersistentToken entity);

    public void update(PersistentToken entity);

    public void delete(PersistentToken entity);
}
