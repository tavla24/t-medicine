package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.exceptions.NullResultFromDBException;
import com.milaev.medicine.service.exceptions.PatientValidationException;
import com.milaev.medicine.utils.PageURLContext;
import com.milaev.medicine.utils.converters.PatientConverter;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dao.PatientDAO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
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
public class PatientService extends AbstractService implements PatientServiceInterface {

    private static Logger log = LoggerFactory.getLogger(PatientService.class);

    @Autowired
    private PatientDAO daoPatient;

    @Autowired
    private DoctorDAO daoDoctor;

    @Autowired
    private AccountDAO daoAccount;


    @Override
    @Transactional
    public ModelAndView mavList() {
        log.info("called PatientService.mavList");
        ModelAndView mav = getPreparedMAV();
        String loggedinuser = (String) mav.getModel().get("loggedinuser");

        Account account = getCurrentAccount(loggedinuser);
        if (account.getRole().getType().equals(RoleType.ADMIN.name()))
            mav.addObject("dto", getAll());
        else
            mav.addObject("dto", getByDoctor(loggedinuser));

        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    private Account getCurrentAccount(String loggedinuser) {
        Account account = daoAccount.getByLogin(loggedinuser);
        if (account == null)
            throw new NullResultFromDBException();
        return account;
    }

    @Override
    @Transactional
    public ModelAndView mavNew() {
        log.info("called PatientService.mavNew");
        ModelAndView mav = getPreparedMAV();
        String loggedinuser = (String) mav.getModel().get("loggedinuser");

        PatientDTO dto = new PatientDTO();
//        Account account = getCurrentAccount(loggedinuser);
//        if (account.getRole().equals(RoleType.DOCTOR.name())){
        Doctor doctor = daoDoctor.getByLogin(loggedinuser);
        if (doctor != null)
            dto.getDoctor().setLogin(doctor.getAccount().getLogin());

        mav.addObject("dto", dto);
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavNew(PatientDTO dto, BindingResult result) {
        log.info("called PatientService.mavNew with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, URI_LIST);

//        DoctorDTO doctorDTO = doctorService.getByLogin(loggedinuser);
//        dto.setDoctor(doctorDTO);
//
//        AccountDTO acc = new AccountDTO();
//        acc.setLogin(dto.getEmail());
//        acc.setPassword(dto.getInsuranceId());
//        RoleDTO role = new RoleDTO();
//        role.setType(RoleType.PATIENT.getUserProfileType());
//        acc.setRole(role);
//
//        accountService.insert(acc);
//
//        //dto.setAccount(acc);
//        patientService.updateProfile(dto, "new");
//
//        return "redirect:/patient/list";
    }

    @Override
    @Transactional
    public ModelAndView mavDelete(String insuranceId) {
        log.info("called PatientService.mavDelete");
        deleteProfile(insuranceId);
        return PageURLContext.getPageRedirect(new ModelAndView(), URI_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(String insuranceId) {
        log.info("called PatientService.mavEdit");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("dto", getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(PatientDTO dto, BindingResult result) {
        log.info("called PatientService.mavEdit with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, URI_MAIN);
//        return null;
//        log.info("updatePatient()");
//        if (result.hasErrors()) {
//            return "patient/registration";
//        }
//        String loggedinuser = sessionAuth.getUserName();
//
//        //log.info(sessionAuth.getUserName());
//        //log.info(doctorDTO.toString());
//        DoctorDTO doctorDTO = doctorService.getByLogin(loggedinuser);//, sessionAuth.getUserName()
//        dto.setDoctor(doctorDTO);
//
//        //dto.getAccount().setLogin(loggedinuser);
//        patientService.updateProfile(dto, insuranceId);
//
//        return "patient/list";
    }

    private void checkDTO(PatientDTO dto, BindingResult result,
                          ModelAndView mav) {
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new PatientValidationException(dto, result, mav);
        }
    }

    @Override
    @Transactional
    public List<PatientDTO> getAll() {
        List<Patient> list = daoPatient.getAll();
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConverter.toDTO(item));
//            PatientDTO dto = PatientConverter.toDTO(item);
//            dto.getDoctor().setLogin(item.getAccount().getLogin());
//            listDAO.add(dto);
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getByLogin(String login) {
        Patient db = daoPatient.getByLogin(login);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConverter.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public PatientDTO getByInsuranceId(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        if (db == null)
            throw new NullResultFromDBException();
        return PatientConverter.toDTO(db);
    }

    @Override
    @Transactional
    public List<PatientDTO> getByDoctor(String login) {
        List<Patient> list = daoPatient.getByDoctorLogin(login);
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConverter.toDTO(item));
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getById(Long id) {
        Patient db = daoPatient.getById(id);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConverter.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public boolean isProfileExist(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        return db != null;
    }

    @Override
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

    @Override
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
