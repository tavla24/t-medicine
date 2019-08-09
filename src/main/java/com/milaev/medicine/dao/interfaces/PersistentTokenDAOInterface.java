package com.milaev.medicine.dao.interfaces;

import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.milaev.medicine.model.PersistentToken;

public interface PersistentTokenDAOInterface extends PersistentTokenRepository {

    PersistentToken getByKey(String key);

    void persist(PersistentToken entity);

    void update(PersistentToken entity);

    void delete(PersistentToken entity);
}
