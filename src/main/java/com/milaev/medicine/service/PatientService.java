package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dao.PatientDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("patientService")
public class PatientService implements PatientServiceInterface {

    private static Logger log = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientDAO daoPatient;

    @Autowired
    private RoleDAO daoRole;

    @Autowired
    private AccountDAO daoAccount;

    @Override
    @Transactional
    public List<PatientDTO> getAll() {
        List<Patient> list = daoPatient.getAll();
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            PatientDTO dto = new PatientDTO();
            MapperUtil.toDTOPatient().accept(item, dto);
            listDAO.add(dto);
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getByLogin(String login) {
        Patient db = daoPatient.getByLogin(login);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            MapperUtil.toDTOPatient().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public PatientDTO getByInsuranceId(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            MapperUtil.toDTOPatient().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public PatientDTO getByFullName(String fname, String surname, String patronymic, String specify) {
        Patient db = daoPatient.getByFullName(fname, surname, patronymic, specify);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            MapperUtil.toDTOPatient().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public List<PatientDTO> getByDoctor(String login) {
        List<Patient> list = daoPatient.getByDoctorLogin(login);
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            PatientDTO dto = new PatientDTO();
            MapperUtil.toDTOPatient().accept(item, dto);
            listDAO.add(dto);
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getById(int id) {
        Patient db = daoPatient.getById(id);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            MapperUtil.toDTOPatient().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public boolean isProfileExist(String login) {
        Patient db = daoPatient.getByLogin(login);
        if (db == null)
            return false;
        return true;
    }

    @Override
    @Transactional
    public boolean isInsuranceIdExist(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        if (db == null)
            return false;
        return true;
    }

    @Override
    @Transactional
    public void updateProfile(PatientDTO dto, String insuranceId) {
        log.info("service.updateProfile(Patient) insuranceId [{}]", insuranceId);
        if (isInsuranceIdExist(insuranceId))
            edit(dto, insuranceId);
        else {
//            AccountDTO acc = new AccountDTO();
//            acc.setLogin(dto.getEmail());
//            acc.setPassword(dto.getInsuranceId());
//            RoleDTO role = new RoleDTO();
//            role.setType(RoleType.PATIENT.getUserProfileType());
//            acc.setRole(role);
//            dto.setAccount(acc);

//            Role r = daoRole.getByType(RoleType.PATIENT.getUserProfileType());
//            Account a = new Account();
//            a.setLogin(dto.getEmail());
//            a.setPassword(dto.getInsuranceId());
//            a.setRole(r);
//            daoAccount.insert(a);

            log.info(dto.toString());
            add(dto);
        }
    }

    @Override
    @Transactional
    public boolean deleteByInsuranceId(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        try {
            daoPatient.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean edit(PatientDTO dto, String insuranceId) {
        log.info("service.update(Patient) insuranceId [{}]", insuranceId);
        //log.info(dto.toString());
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        //db.setLogin(oldLogin);
        fillDTODataToEntity(dto, db);
        try {
            daoPatient.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean add(PatientDTO dto) {
        log.info("service.insert(Doctor)");
        Patient db = new Patient();
        fillDTODataToEntity(dto, db);
        try {
            daoPatient.insert(db);
            log.info("!!! done");
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void fillDTODataToEntity(PatientDTO dto, Patient entity) {
        // TODO question - is it normal way?
        log.info("fillDTODataToEntity");
        log.info(dto.toString());
        //Doctor d = daoDoctor.getByLogin(dto.getDoctor().getAccount().getLogin());
        //d.setId(0);
        //log.info("!!! d: {}", d.getSpecialization());
        //Role r = daoRole.getByType(a.getRole().getType());
        //log.info("!!! r: {}", r.getType());
        //a.setRole(r);
        //log.info("!!! a: {}", a.toString());
        //entity.setDoctor(d);
        MapperUtil.toEntityPatient().accept(dto, entity);
//        Role role = daoRole.getByType(RoleType.PATIENT.getUserProfileType());
//        Account a = daoAccount.getByLogin(dto.getAccount().getLogin());
//        a.setLogin(entity.getEmail());
//        a.setPassword(entity.getInsuranceId());
//        entity.setAccount(a);
//        entity.getAccount().setRole(role);
        //log.info("!!! entity role: {}", entity.getAccount().getRole().getType());

        // TODO sometime work, sometime not
        //entity.setDoctor(d);
        //log.info("!!! entity role: {}", entity.getAccount().getRole().getType());
    }
}
