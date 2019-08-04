package com.milaev.medicine.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.MapperUtil;

@Service("doctorService")
public class DoctorService implements DoctorServiceInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorDAO daoDoctor;
    @Autowired
    private AccountDAO daoAccount;

    @Override
    @Transactional
    public List<DoctorDTO> getAll() {
        List<Doctor> list = daoDoctor.getAll();
        List<DoctorDTO> listDAO = new ArrayList<>();
        for (Doctor item : list) {
            DoctorDTO doctorDTO = new DoctorDTO();
            MapperUtil.toDTODoctor().accept(item, doctorDTO);
            listDAO.add(doctorDTO);
        }
        return listDAO;
    }

    @Override
    @Transactional
    public DoctorDTO getByLogin(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            MapperUtil.toDTODoctor().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify) {
        Doctor dbDoctor = daoDoctor.getByFullName(fname, surname, patronymic, specify);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            MapperUtil.toDTODoctor().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getById(int id) {
        Doctor dbDoctor = daoDoctor.getById(id);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            MapperUtil.toDTODoctor().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }


    @Override
    @Transactional
    public boolean deleteByLogin(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        try {
            daoDoctor.delete(dbDoctor);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean isProfileExist(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        if (dbDoctor == null)
            return false;
        return true;
    }

    @Override
    @Transactional
    public void updateProfile(DoctorDTO doctorDTO, String login) {
        log.info("service.updateProfile(Doctor) login [{}]", login);
        if (isProfileExist(login))
            edit(doctorDTO, login);
        else
            add(doctorDTO);
    }

    @Override
    @Transactional
    public boolean edit(DoctorDTO dto, String oldLogin) {
        // TODO point 2
        log.info("service.update(Doctor) login [{}]", oldLogin);
        //log.info(dto.toString());
        Doctor dbDoctor = daoDoctor.getByLogin(oldLogin);
        //dbDoctor.setLogin(oldLogin);
        fillDTODataToEntity(dto, dbDoctor);
        try {
            daoDoctor.update(dbDoctor);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
    }
        return true;
    }

    @Override
    @Transactional
    public boolean add(DoctorDTO dto) {
        log.info("service.insert(Doctor)");
        Doctor dbDoctor = new Doctor();
        fillDTODataToEntity(dto, dbDoctor);
        try {
            daoDoctor.insert(dbDoctor);
            log.info("!!! done");
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void fillDTODataToEntity(DoctorDTO dto, Doctor entity) {
        // TODO question - is it normal way?
        log.info("fillDTODataToEntity");
        log.info(dto.toString());
        Account a = daoAccount.getByLogin(dto.getAccount().getLogin());
        log.info("!!! a: {}", a.toString());
        //Role r = daoRole.getByType(a.getRole().getType());
        //log.info("!!! r: {}", r.getType());
        //a.setRole(r);
        //log.info("!!! a: {}", a.toString());
        entity.setAccount(a);
        MapperUtil.toEntityDoctor().accept(dto, entity);
        log.info("!!! entity role: {}", entity.getAccount().getRole().getType());

        // TODO sometime work, sometime not
        entity.setAccount(a);
        log.info("!!! entity role: {}", entity.getAccount().getRole().getType());
    }
}
