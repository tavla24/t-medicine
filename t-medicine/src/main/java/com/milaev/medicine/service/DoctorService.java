package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.exceptions.DoctorValidationException;
import com.milaev.medicine.exceptions.NullResultFromDBException;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.utils.converters.DoctorConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service("doctorService")
public class DoctorService extends AbstractService {

    private static Logger log = LoggerFactory.getLogger(DoctorService.class);

    public static String PAGE_LIST = "doctor/list";
    public static String PAGE_REGISTRATION = "doctor/registration";
    public static String URI_LIST = "/admin/doctor/list";

    @Autowired
    private DoctorDAO daoDoctor;

    @Autowired
    private AccountDAO daoAccount;

    @Autowired
    private AccountService accountService;

    public void checkDTO(DoctorDTO dto, BindingResult result,
                          ModelAndView mav){
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new DoctorValidationException(dto, result, mav);
        }
    }

    @Transactional
    public List<DoctorDTO> getAll() {
        List<Doctor> list = daoDoctor.getAll();
        List<DoctorDTO> listDAO = new ArrayList<>();
        for (Doctor item : list) {
            listDAO.add(fillDTO(item));
        }
        return listDAO;
    }

    @Transactional
    public DoctorDTO getByLogin(String login) {
        return fillDTO(getEntityByLogin(login));
    }

    private Doctor getEntityByLogin(String login) {
        Doctor db = daoDoctor.getByLogin(login);
        if (db == null)
            throw new NullResultFromDBException();
        return db;
    }

    @Transactional
    public DoctorDTO getById(Long id) {
        Doctor dbDoctor = daoDoctor.getById(id);
        return fillDTO(dbDoctor);
    }

    @Transactional
    public void deleteProfile(String login) {
        Doctor dbDoctor = getEntityByLogin(login);
        try {
            daoDoctor.delete(dbDoctor);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Transactional
    public boolean isProfileExist(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        return dbDoctor != null;
    }

    @Transactional
    public void updateProfile(DoctorDTO dto) {
        log.info("service.updateProfile(Doctor) login [{}]", dto.getLogin());
        Doctor dbDoctor = daoDoctor.getByLogin(dto.getLogin());

        if (dbDoctor == null)
            insert(dto, new Doctor());
        else
            update(dto, dbDoctor);
    }

    private void update(DoctorDTO dto, Doctor db) {
        log.info("service.update(Doctor) login [{}]", db.getAccount().getLogin());
        fillEntity(dto, db);
        try {
            daoDoctor.update(db);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private void insert(DoctorDTO dto, Doctor db) {
        log.info("service.insert(Doctor)");
        fillEntity(dto, db);
        try {
            daoDoctor.insert(db);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private void fillEntity(DoctorDTO dto, Doctor entity) {
        log.info("fillEntity");

        if (accountService.isLoginUnique(dto.getLogin())){
            AccountDTO accountDTO = new AccountDTO();
            RoleDTO roleDTO = new RoleDTO();
            roleDTO.setType(RoleType.DOCTOR.name());
            accountDTO.setLogin(dto.getLogin());
            accountDTO.setPassword(dto.getPhone());
            accountDTO.setRole(roleDTO);
            accountService.insert(accountDTO);
        }

        Account a = daoAccount.getByLogin(dto.getLogin());
        entity.setAccount(a);
        DoctorConverter.toEntity(dto, entity);
    }

    private DoctorDTO fillDTO(Doctor db) {
        log.info("called AccountService.fillDTO with db");
        if (db != null) {
            DoctorDTO dto = DoctorConverter.toDTO(db);
            return dto;
        }
        return null;
    }

    private void report(Exception ex) {
        log.error("Exception from Service during DB query");
        ex.printStackTrace();
        throw new NullResultFromDBException();
    }
}
