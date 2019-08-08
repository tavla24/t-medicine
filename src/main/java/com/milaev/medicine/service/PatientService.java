package com.milaev.medicine.service;

import com.milaev.medicine.converter.PatientConv;
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
    private DoctorDAO daoDoctor;

    @Override
    @Transactional
    public List<PatientDTO> getAll() {
        List<Patient> list = daoPatient.getAll();
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConv.toDTO(item));
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getByLogin(String login) {
        Patient db = daoPatient.getByLogin(login);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConv.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public PatientDTO getByInsuranceId(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConv.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public PatientDTO getByFullName(String fname, String surname, String patronymic, String specify) {
        Patient db = daoPatient.getByFullName(fname, surname, patronymic, specify);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConv.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public List<PatientDTO> getByDoctor(String login) {
        List<Patient> list = daoPatient.getByDoctorLogin(login);
        List<PatientDTO> listDAO = new ArrayList<>();
        for (Patient item : list) {
            listDAO.add(PatientConv.toDTO(item));
        }
        return listDAO;
    }

    @Override
    @Transactional
    public PatientDTO getById(Long id) {
        Patient db = daoPatient.getById(id);
        PatientDTO dto = new PatientDTO();
        if (db != null)
            dto = PatientConv.toDTO(db);
        return dto;
    }

    @Override
    @Transactional
    public boolean isProfileExist(String insuranceId) {
        Patient db = daoPatient.getByInsuranceId(insuranceId);
        if (db == null)
            return false;
        return true;
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
    public void updateProfile(PatientDTO dto, String insuranceId) {
        log.info("service.updateProfile(Patient) insuranceId [{}]", insuranceId);
        Patient db = daoPatient.getByInsuranceId(insuranceId);

        if (db == null)
            add(dto, new Patient());
        else
            edit(dto, db);
    }

    private void edit(PatientDTO dto, Patient db) {
        log.info("service.update(Patient) insuranceId [{}]", dto.getInsuranceId());
        fillDTODataToEntity(dto, db);
        try {
            daoPatient.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void add(PatientDTO dto, Patient db) {
        log.info("service.insert(Doctor)");
        fillDTODataToEntity(dto, db);
        try {
            daoPatient.insert(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void fillDTODataToEntity(PatientDTO dto, Patient entity) {
        log.info("fillDTODataToEntity");
        Doctor d = daoDoctor.getByLogin(dto.getDoctor().getLogin());
        MapperUtil.toEntityPatient().accept(dto, entity);
        entity.setDoctor(d);
    }
}
