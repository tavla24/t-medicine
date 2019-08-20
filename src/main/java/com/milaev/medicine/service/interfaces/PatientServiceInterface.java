package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.PatientDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface PatientServiceInterface {

    String PAGE_LIST = "patient/list";
    String PAGE_REGISTRATION = "patient/registration";

    String URI_LIST = "/patient/list";
    String URI_MAIN = "/";

    ModelAndView mavList();

    ModelAndView mavNew();

    ModelAndView mavNew(PatientDTO dto, BindingResult result);

    ModelAndView mavDelete(String login);

    ModelAndView mavEdit(String login);

    ModelAndView mavEdit(PatientDTO dto, BindingResult result);

    List<PatientDTO> getAll();

    List<PatientDTO> getByDoctor(String login);

    PatientDTO getById(Long id);

    PatientDTO getByLogin(String login);

    PatientDTO getByInsuranceId(String insuranceId);

    boolean isProfileExist(String insuranceId);

    void updateProfile(PatientDTO dto);

    void deleteProfile(String insuranceId);
}
