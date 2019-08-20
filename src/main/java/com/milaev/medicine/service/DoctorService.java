package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.service.exceptions.DoctorValidationException;
import com.milaev.medicine.service.exceptions.NullResultFromDBException;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
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
public class DoctorService extends AbstractService implements DoctorServiceInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorService.class);

    @Autowired
    private DoctorDAO daoDoctor;

    @Autowired
    private AccountDAO daoAccount;

    @Override
    @Transactional
    public ModelAndView mavList() {
        log.info("called DoctorService.mavList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("dto", getAll());
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavNew() {
        log.info("called DoctorService.mavNew");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("dto", new DoctorDTO());
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavNew(DoctorDTO dto, BindingResult result) {
        log.info("called DoctorService.mavNew with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, URI_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(String login) {
        log.info("called DoctorService.mavEdit");
        ModelAndView mav = getPreparedMAV();

        DoctorDTO dto;
        if (login.isEmpty()){
            String loggedinuser = (String) mav.getModel().get("loggedinuser");
            if (isProfileExist(loggedinuser))
                dto = getByLogin(loggedinuser);
            else {
                dto = new DoctorDTO();
                dto.setLogin(loggedinuser);
            }
        } else
            dto = getByLogin(login);

        dto.setEdit(true);
        mav.addObject("dto", dto);

        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(DoctorDTO dto, BindingResult result) {
        log.info("called DoctorService.mavEdit with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, URI_MAIN);
    }

    @Override
    @Transactional
    public ModelAndView mavDelete(String login) {
        log.info("called DoctorService.mavDelete");
        deleteProfile(login);
        return PageURLContext.getPageRedirect(new ModelAndView(), URI_LIST);
    }

    private void checkDTO(DoctorDTO dto, BindingResult result,
                          ModelAndView mav){
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new DoctorValidationException(dto, result, mav);
        }
    }

    @Override
    @Transactional
    public List<DoctorDTO> getAll() {
        List<Doctor> list = daoDoctor.getAll();
        List<DoctorDTO> listDAO = new ArrayList<>();
        for (Doctor item : list) {
            listDAO.add(fillDTO(item));
        }
        return listDAO;
    }

    @Override
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

    @Override
    @Transactional
    public DoctorDTO getById(Long id) {
        Doctor dbDoctor = daoDoctor.getById(id);
        return fillDTO(dbDoctor);
    }

    @Override
    @Transactional
    public void deleteProfile(String login) {
        Doctor dbDoctor = getEntityByLogin(login);
        try {
            daoDoctor.delete(dbDoctor);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public boolean isProfileExist(String login) {
        Doctor dbDoctor = daoDoctor.getByLogin(login);
        return dbDoctor != null;
    }

    @Override
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
