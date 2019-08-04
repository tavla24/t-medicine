package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.PatientDTO;

import java.util.List;

public interface PatientServiceInterface {

    List<PatientDTO> getAll();

    PatientDTO getByLogin(String login);

    PatientDTO getByInsuranceId(String insuranceId);

    List<PatientDTO> getByDoctor(String login);

    PatientDTO getByFullName(String fname, String surname, String patronymic, String specify);

    PatientDTO getById(int id);

    //boolean isLoginUnique(String login);
    boolean isProfileExist(String login);

    boolean isInsuranceIdExist(String insuranceId);

    void updateProfile(PatientDTO dto, String insuranceId);

    boolean deleteByInsuranceId(String insuranceId);

    boolean edit(PatientDTO dto, String insuranceId);

    boolean add(PatientDTO dto);
}
