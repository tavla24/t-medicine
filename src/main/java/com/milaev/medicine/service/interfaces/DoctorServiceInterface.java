package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.ViewDoctorDTO;
import com.milaev.medicine.model.Doctor;

public interface DoctorServiceInterface {

    List<DoctorDTO> getAll();

    DoctorDTO getByLogin(String login);

    DoctorDTO getByFullName(String fname, String surname, String patronymic, String specify);

    DoctorDTO getById(int id);

    //boolean isLoginUnique(String login);
    boolean isProfileExist(String login);

    void updateProfile(ViewDoctorDTO dto);

    void updateProfile(ViewDoctorDTO dto, String login);

    boolean deleteByLogin(String login);

//    boolean edit(ViewDoctorDTO dto, Doctor db);
//
//    boolean add(ViewDoctorDTO dto, Doctor db);
}
