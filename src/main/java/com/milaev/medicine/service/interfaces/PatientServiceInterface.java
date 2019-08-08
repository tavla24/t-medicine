package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Patient;

import java.util.List;

public interface PatientServiceInterface {

    List<PatientDTO> getAll();

    PatientDTO getByLogin(String login);

    PatientDTO getByInsuranceId(String insuranceId);

    List<PatientDTO> getByDoctor(String login);

    PatientDTO getByFullName(String fname, String surname, String patronymic, String specify);

    PatientDTO getById(Long id);

    boolean isProfileExist(String insuranceId);

    void updateProfile(PatientDTO dto, String insuranceId);

    void deleteProfile(String insuranceId);
}
