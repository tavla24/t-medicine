package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.dto.DoctorDTO;

public interface DoctorServiceInterface {

    List<DoctorDTO> getAll();

    DoctorDTO getByLogin(String login);

    DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify);

    DoctorDTO getById(int id);

    //boolean isLoginUnique(String login);
    boolean isProfileExist(String login);

    void updateProfile(DoctorDTO doctorDTO, String login);

    boolean deleteByLogin(String login);

    boolean edit(DoctorDTO dto, String oldLogin);

    boolean add(DoctorDTO dto);
}
