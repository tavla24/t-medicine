package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.PersistentToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public interface PersistentTokenDAOInterface extends PersistentTokenRepository {

    PersistentToken getByKey(String key);

    void persist(PersistentToken entity);

    void update(PersistentToken entity);

    void delete(PersistentToken entity);
}
