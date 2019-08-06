package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dao.interfaces.PersistentTokenDAOInterface;
import com.milaev.medicine.model.PersistentToken;

public interface TPersistentTokenServiceInterface extends PersistentTokenDAOInterface {

    PersistentToken getByKey(String key);

    void persist(PersistentToken entity);

    void update(PersistentToken entity);

    void delete(PersistentToken entity);
}
