package com.milaev.medicine.service;

import com.milaev.medicine.dao.interfaces.PersonDAOInterface;
import com.milaev.medicine.model.Person;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.MapperUtil2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public abstract class PersonService<E extends Person, D>{

    private static Logger log = LoggerFactory.getLogger(PersonService.class);

    private final Class<E> classE;
    private final Class<D> classD;

    @SuppressWarnings("unchecked")
    public PersonService() {
        this.classE = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.classD = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public abstract PersonDAOInterface<E> getDAO();

    public List<D> getAll(){
        List<E> list = getDAO().getAll();
        List<D> listDAO = new ArrayList<>();
        for (E item : list) {
            D dto = null;
            try {
                dto = classD.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            (new MapperUtil2<E, D>()).toDTO().accept(item, dto);
            listDAO.add(dto);
        }
        return listDAO;
    }

    public D getByLogin(String login){
        E db = getDAO().getByLogin(login);
        D dto = null;
        try {
            dto = classD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public D getByFullName(String fname, String surname, String patronymic, String specify){
        E db = getDAO().getByFullName(fname, surname, patronymic, specify);
        D dto = null;
        try {
            dto = classD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public D getById(Long id){
        E db = getDAO().getById(id);
        D dto = null;
        try {
            dto = classD.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (db != null)
            (new MapperUtil2<E, D>()).toDTO().accept(db, dto);
        return dto;
    }

    public void deleteByLogin(String login) {
        E db = getDAO().getByLogin(login);
        try {
            getDAO().delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    public boolean isProfileExist(String login) {
        E db = getDAO().getByLogin(login);
        if (db == null)
            return false;
        return true;
    }

    public void updateProfile(D dto, String login){
        log.info("service.updateProfile(Doctor) login [{}]", login);
        if (isProfileExist(login))
            edit(dto, login);
        else
            add(dto);
    }

    public void edit(D dto, String oldLogin) {
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
        }
    }

    public void add(D dto){
        log.info("service.insert(Doctor)");
        E db = null;
        try {
            db = classE.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        fillDTODataToEntity(dto, db);
        try {
            getDAO().insert(db);
            log.info("!!! done");
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    public abstract void fillDTODataToEntity(D dto, E entity);
}
