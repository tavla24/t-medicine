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
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.MapperDoctors;

@Service
public class DoctorService implements DoctorServiceInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorDAO daoDoctor;
    @Autowired
    private AccountDAO daoAccount;
    @Autowired
    private RoleDAO daoRole;

    @Override
    @Transactional
    public List<DoctorDTO> getAll() {

        List<Doctor> list = daoDoctor.getAll();
        List<DoctorDTO> listDAO = new ArrayList<>();
        for (Doctor item : list) {
            DoctorDTO doctorDTO = new DoctorDTO();
            MapperDoctors.toDTO().accept(item, doctorDTO);
            listDAO.add(doctorDTO);
        }

        return listDAO;
    }

    @Override
    @Transactional
    public DoctorDTO getByLogin(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        DoctorDTO doctorDTO = new DoctorDTO();
        MapperDoctors.toDTO().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify) {
        Doctor dbDoctor = daoDoctor.getByFullName(fname, surname, patronymic, specify);
        DoctorDTO doctorDTO = new DoctorDTO();
        MapperDoctors.toDTO().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getById(int id) {
        Doctor dbDoctor = daoDoctor.getById(id);
        DoctorDTO doctorDTO = new DoctorDTO();
        MapperDoctors.toDTO().accept(dbDoctor, doctorDTO);
        return doctorDTO;
    }

    @Override
    @Transactional
    public boolean deleteByLogin(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        try {
            daoDoctor.delete(dbDoctor);
        } catch (Exception ex) {
            log.info("something wrong with delete");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean edit(DoctorDTO dto, String oldLogin) {
        log.info("service.edit(Doctor)");
        // TODO question - is it normal way?
        Doctor dbDoctor = daoDoctor.getByLogin(oldLogin);
        Account a = daoAccount.getByLogin(oldLogin);
        Role r = daoRole.getByType(a.getRole());
        a.setRole(r);
        dbDoctor.setAccount(a);
        MapperDoctors.toEntity().accept(dto, dbDoctor);
        try {
            daoDoctor.edit(dbDoctor);
        } catch (Exception ex) {
            log.info("something wrong with edit");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean add(DoctorDTO dto) {
        log.info("service.add(Doctor)");
        Account a = daoAccount.getByLogin(dto.getLogin());
        Role r = daoRole.getByType(a.getRole());
        log.info("!!! r: {}", r.getType());
        a.setRole(r);
        log.info("!!! a: {}", a.toString());
        Doctor dbDoctor = new Doctor();
        MapperDoctors.toEntity().accept(dto, dbDoctor);
        dbDoctor.setAccount(a);
        log.info("!!! dbDoctor role: {}", dbDoctor.getAccount().getRole());
        try {
            daoDoctor.add(dbDoctor);
            log.info("!!! done");
        } catch (Exception ex) {
            log.info("something wrong with insert");
            return false;
        }
        return true;
    }
}
