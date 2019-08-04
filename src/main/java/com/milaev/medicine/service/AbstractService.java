package com.milaev.medicine.service;

import com.milaev.medicine.dao.interfaces.AbstractDAOInterface;
import com.milaev.medicine.utils.MapperUtil;
import com.milaev.medicine.utils.MapperUtil2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractService<E, D> {

    private static Logger log = LoggerFactory.getLogger(AbstractService.class);

    private final Class<E> classE;
    private final Class<D> classD;

    @SuppressWarnings("unchecked")
    public AbstractService() {
        this.classE = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.classD = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public abstract AbstractDAOInterface<E> getDAO();

    public List<D> getAll() throws IllegalAccessException, InstantiationException {
        List<E> list = getDAO().getAll();
        List<D> listDAO = new ArrayList<>();
        for (E item : list) {
            D dto = classD.newInstance();
            (new MapperUtil2<E, D>()).toDTO().accept(item, dto);
            listDAO.add(dto);
        }
        return listDAO;
    }

    public D getByLogin(String login) throws IllegalAccessException, InstantiationException {
        E db = getDAO().getByLogin(login);
        D dto = classD.newInstance();
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public D getByFullName(String fname, String surname, String patronymic, String specify) throws IllegalAccessException, InstantiationException {
        E db = getDAO().getByFullName(fname, surname, patronymic, specify);
        D dto = classD.newInstance();
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public D getById(int id) throws IllegalAccessException, InstantiationException {
        E db = getDAO().getById(id);
        D dto = classD.newInstance();
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public boolean deleteByLogin(String login) {
        E db = getDAO().getByLogin(login);
        try {
            getDAO().delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean isProfileExist(String login) {
        E db = getDAO().getByLogin(login);
        if (db == null)
            return false;
        return true;
    }

    public void updateProfile(D dto, String login) throws InstantiationException, IllegalAccessException {
        log.info("service.updateProfile(Doctor) login [{}]", login);
        if (isProfileExist(login))
            edit(dto, login);
        else
            add(dto);
    }

    public boolean edit(D dto, String oldLogin) {
        log.info("service.update(Doctor) login [{}]", oldLogin);
        //log.info(dto.toString());
        E db = getDAO().getByLogin(oldLogin);
        //dbDoctor.setLogin(oldLogin);
        fillDTODataToEntity(dto, db);
        try {
            getDAO().update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean add(D dto) throws IllegalAccessException, InstantiationException {
        log.info("service.insert(Doctor)");
        E db = classE.newInstance();
        fillDTODataToEntity(dto, db);
        try {
            getDAO().insert(db);
            log.info("!!! done");
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    public abstract void fillDTODataToEntity(D dto, E entity);
}
