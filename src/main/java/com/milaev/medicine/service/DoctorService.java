package com.milaev.medicine.service;

import java.util.ArrayList;
import java.util.List;

import com.milaev.medicine.converter.DoctorConv;
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
            listDAO.add(DoctorConv.toDTO(item));
        }
        return listDAO;
    }

    @Override
    @Transactional
    public DoctorDTO getByLogin(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            doctorDTO = DoctorConv.toDTO(dbDoctor);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify) {
        Doctor dbDoctor = daoDoctor.getByFullName(fname, surname, patronymic, specify);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            doctorDTO = DoctorConv.toDTO(dbDoctor);
        return doctorDTO;
    }

    @Override
    @Transactional
    public DoctorDTO getById(int id) {
        Doctor dbDoctor = daoDoctor.getById(id);
        DoctorDTO doctorDTO = new DoctorDTO();
        if (dbDoctor != null)
            doctorDTO = DoctorConv.toDTO(dbDoctor);
        return doctorDTO;
    }


    @Override
    @Transactional
    public void deleteProfile(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        try {
            daoDoctor.delete(dbDoctor);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
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
    public void updateProfile(DoctorDTO dto) {
        log.info("service.updateProfile(Doctor) login [{}]", dto.getLogin());
        Doctor dbDoctor = daoDoctor.getByLogin(dto.getLogin());

        if (dbDoctor == null)
            add(dto, new Doctor());
        else
            edit(dto, dbDoctor);

    }

    private void edit(DoctorDTO dto, Doctor db) {
        log.info("service.update(Doctor) login [{}]", db.getAccount().getLogin());
        fillDTODataToEntity(dto, db);
        try {
            daoDoctor.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
    }
    }

    private void add(DoctorDTO dto, Doctor db) {
        log.info("service.insert(Doctor)");
        fillDTODataToEntity(dto, db);
        try {
            daoDoctor.insert(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void fillDTODataToEntity(DoctorDTO dto, Doctor entity) {
        log.info("fillDTODataToEntity");
        Account a = daoAccount.getByLogin(dto.getLogin());
        entity.setAccount(a);
        MapperUtil.toEntityDoctor().accept(dto, entity);
        log.info("!!! entity role: {}", entity.getAccount().getRole().getType());
    }
}
