package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.dto.DoctorDTO;

public interface DoctorServiceInterface {

    List<DoctorDTO> getAll();

    DoctorDTO getByLogin(String login);

    DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify);

    DoctorDTO getById(Long id);

    boolean isProfileExist(String login);

    void updateProfile(DoctorDTO doctorDTO);

    void deleteProfile(String login);
}
