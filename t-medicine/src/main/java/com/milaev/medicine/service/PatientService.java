package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dao.PatientDAO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.exceptions.NullResultFromDBException;
import com.milaev.medicine.exceptions.PatientValidationException;
import com.milaev.medicine.utils.PageURLContext;
import com.milaev.medicine.utils.converters.PatientConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service("patientService")
public class PatientService extends AbstractService {

    private static Logger log = LoggerFactory.getLogger(PatientService.class);

    public static String PAGE_LIST = "patient/list";
    public static String PAGE_REGISTRATION = "patient/registration";
    public static String URI_LIST = "/patient/list";

    @Autowired
    private PatientDAO daoPatient;

    @Autowired
    private DoctorDAO daoDoctor;

    @Autowired
    private AccountDAO daoAccount;

    public void checkDTO(PatientDTO dto, BindingResult result,
                          ModelAndView mav) {
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new PatientValidationException(dto, result, mav);
        }
    }

    @Transactional
    public List<PatientDTO> getAll() {
        List<Patient> list = daoPatient.getAll();
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConverter.toDTO(item));
        }
        return listDAO;
    }

    @Transactional
    public PatientDTO getByLogin(String login) {
        Patient db = daoPatient.getByLogin(login);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConverter.toDTO(db);
        return dto;
    }

    @Transactional
    public PatientDTO getByInsuranceId(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        if (db == null)
            throw new NullResultFromDBException();
        return PatientConverter.toDTO(db);
    }

    @Transactional
    public List<PatientDTO> getByDoctor(String login) {
        List<Patient> list = daoPatient.getByDoctorLogin(login);
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConverter.toDTO(item));
        }
        return listDAO;
    }

    @Transactional
    public List<PatientDTO> getByAccount(String loggedinuser){
        Account account = getCurrentAccount(loggedinuser);

        if (account.getRole().getType().equals(RoleType.ADMIN.name()))
            return getAll();
        else
            return getByDoctor(loggedinuser);
    }

    private Account getCurrentAccount(String loggedinuser) {
        Account account = daoAccount.getByLogin(loggedinuser);
        if (account == null)
            throw new NullResultFromDBException();
        return account;
    }

    @Transactional
    public PatientDTO getPatientDTOwithDoctor(String loggedinuser){
        PatientDTO dto = new PatientDTO();
        Doctor doctor = daoDoctor.getByLogin(loggedinuser);
        if (doctor != null)
            dto.getDoctor().setLogin(doctor.getAccount().getLogin());
        return dto;
    }

    @Transactional
    public PatientDTO getById(Long id) {
        Patient db = daoPatient.getById(id);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConverter.toDTO(db);
        return dto;
    }

    @Transactional
    public boolean isProfileExist(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        return db != null;
    }

    @Transactional
    public void deleteProfile(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        try {
            daoPatient.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    @Transactional
    public void updateProfile(PatientDTO dto) {
        log.info("service.updateProfile");
        Patient db = daoPatient.getByInsuranceId(dto.getOldInsuranceId());

        if (db == null)
            add(dto, new Patient());
        else
            edit(dto, db);
    }

    private void edit(PatientDTO dto, Patient db) {
        log.info("service.update(Patient) insuranceId [{}]", dto.getInsuranceId());
        fillEntity(dto, db);
        try {
            daoPatient.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void add(PatientDTO dto, Patient db) {
        log.info("service.insert(Doctor)");
        fillEntity(dto, db);
        try {
            daoPatient.insert(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void fillEntity(PatientDTO dto, Patient entity) {
        log.info("fillEntity");
        Doctor d = daoDoctor.getByLogin(dto.getDoctor().getLogin());
        entity.setDoctor(d);
        PatientConverter.toEntity(dto, entity);
    }
}
