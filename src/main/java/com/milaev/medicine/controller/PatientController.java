package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.dto.validators.PatientValidator;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private static Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private PatientServiceInterface patientService;

    @Autowired
    private PatientValidator patientValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(patientValidator);
    }

    @GetMapping(value = "/list")
    public ModelAndView listPatients() {
        log.info("[/patient] get request for url /list");
        return patientService.mavList();
    }

    @GetMapping(value = {"/edit/{insuranceId}"})
    public ModelAndView editPatient(@PathVariable String insuranceId) {
        log.info("[/patient] get request for url /edit/{}", insuranceId);
        return patientService.mavEdit(insuranceId);
    }

    @PostMapping("/edit/{insuranceId}")
    public ModelAndView editPatient(@Validated PatientDTO dto, BindingResult result, @PathVariable String insuranceId) {
        log.info("[/patient] post request for url /edit/{}", insuranceId);
        return patientService.mavEdit(dto, result);
    }

    // @DeleteMapping(value = { "/delete-user-{login}" })
    @GetMapping(value = {"/delete/{insuranceId}"})
    public ModelAndView deletePatient(@PathVariable String insuranceId) {
        log.info("[/patient] get request for url /delete/{}", insuranceId);
        return patientService.mavDelete(insuranceId);
    }

    @GetMapping(value = {"/new"})
    public ModelAndView newPatient() {
        log.info("[/patient] get request for url /new");
        return patientService.mavNew();
    }

    @PostMapping(value = {"/new"})
    public ModelAndView newPatient(@Validated PatientDTO dto, BindingResult result) {
        log.info("[/patient] post request for url /new");
        return patientService.mavNew(dto, result);
    }
}
