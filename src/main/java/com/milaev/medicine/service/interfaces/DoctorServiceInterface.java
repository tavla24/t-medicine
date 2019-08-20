package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.DoctorDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface DoctorServiceInterface {

    String PAGE_LIST = "doctor/list";
    String PAGE_REGISTRATION = "doctor/registration";

    String URI_LIST = "/admin/doctor/list";
    String URI_MAIN = "/";

    ModelAndView mavList();

    ModelAndView mavNew();

    ModelAndView mavNew(DoctorDTO dto, BindingResult result);

    ModelAndView mavDelete(String login);

    ModelAndView mavEdit(String login);

    ModelAndView mavEdit(DoctorDTO dto, BindingResult result);

    List<DoctorDTO> getAll();

    DoctorDTO getByLogin(String login);

    DoctorDTO getById(Long id);

    boolean isProfileExist(String login);

    void updateProfile(DoctorDTO doctorDTO);

    void deleteProfile(String login);
}
