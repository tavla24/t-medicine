package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dao.interfaces.PersistentTokenDAOInterface;
import com.milaev.medicine.model.PersistentToken;

public interface TPersistentTokenServiceInterface extends PersistentTokenDAOInterface {

    public PersistentToken getByKey(String key);

    public void persist(PersistentToken entity);

    public void update(PersistentToken entity);

    public void delete(PersistentToken entity);
}
