package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.DoctorDTO;

import java.util.List;

public interface DoctorServiceInterface {

    List<DoctorDTO> getAll();

    DoctorDTO getByLogin(String login);

    DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify);

    DoctorDTO getById(Long id);

    boolean isProfileExist(String login);

    void updateProfile(DoctorDTO doctorDTO);

    void deleteProfile(String login);
}
