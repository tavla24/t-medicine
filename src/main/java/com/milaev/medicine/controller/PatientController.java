package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.dto.RoleDTO;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private static Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PatientServiceInterface patientService;

    @Autowired
    DoctorServiceInterface doctorService;

    @Autowired
    AccountServiceInterface accountService;

    @GetMapping(value = "/")
    public String patient(ModelMap model) {
        log.info("patient()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "patient/mainpanel";
    }

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public String listPatients(ModelMap model) {
        log.info("listPatients()");
        String loggedinuser = sessionAuth.getUserName();
        List<PatientDTO> patients = patientService.getByDoctor(loggedinuser);
        model.addAttribute("patients", patients);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "patient/list";
    }

    @GetMapping(value = {"/edit/{insuranceId}"})
    public String editPatient(@PathVariable String insuranceId, ModelMap model) {
        log.info("editPatient()");
        String loggedinuser = sessionAuth.getUserName();

        PatientDTO dto = patientService.getByInsuranceId(insuranceId);
        if (dto == null) {
            dto = new PatientDTO();
            //dto.getDoctor().getAccount().setLogin(loggedinuser);
        }

        model.addAttribute("patient", dto);
        model.addAttribute("statuses", PatientStatus.getPatientStatusList());
        model.addAttribute("loggedinuser", loggedinuser);
        return "patient/registration";
    }

    @PostMapping("/edit/{insuranceId}")
    public String updatePatient(@PathVariable String insuranceId, @Valid PatientDTO dto, BindingResult result, ModelMap model) {
        log.info("updatePatient()");
        if (result.hasErrors()) {
            return "patient/registration";
        }
        String loggedinuser = sessionAuth.getUserName();

        //log.info(sessionAuth.getUserName());
        //log.info(doctorDTO.toString());
        DoctorDTO doctorDTO = doctorService.getByLogin(loggedinuser);//, sessionAuth.getUserName()
        //dto.setDoctor(doctorDTO);

        //dto.getAccount().setLogin(loggedinuser);
        patientService.updateProfile(dto, insuranceId);

        return "patient/list";
    }

    // @DeleteMapping(value = { "/delete-user-{login}" })
    @GetMapping(value = {"/delete/{insuranceId}"})
    public String deletePatient(@PathVariable String insuranceId) {
        log.info("deletePatient()");
        patientService.deleteByInsuranceId(insuranceId);
        return "redirect:/patient/list";
    }

    @GetMapping(value = {"/new"})
    public String newPatient(ModelMap model) {
        log.info("newPatient()");
        if (!model.containsAttribute("patient")) {
            PatientDTO dto = new PatientDTO();
            model.addAttribute("patient", dto);
            model.addAttribute("statuses", PatientStatus.getPatientStatusList());
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "patient/registration";
    }

    @PostMapping(value = {"/new"})
    public String savePatient(@Valid PatientDTO dto, BindingResult result, ModelMap model,
                              RedirectAttributes redirectAttributes) {
        String loggedinuser = sessionAuth.getUserName();
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("patient", dto);
        redirectAttributes.addFlashAttribute("loggedinuser", loggedinuser);
        redirectAttributes.addFlashAttribute("statuses", PatientStatus.getPatientStatusList());
        log.info("savePatient()");

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            return "redirect:/patient/new";
        }

        log.info("no validation errors");
        DoctorDTO doctorDTO = doctorService.getByLogin(loggedinuser);
        //dto.setDoctor(doctorDTO);

        AccountDTO acc = new AccountDTO();
        acc.setLogin(dto.getEmail());
        acc.setPassword(dto.getInsuranceId());
        RoleDTO role = new RoleDTO();
        role.setType(RoleType.PATIENT.getUserProfileType());
        acc.setRole(role);
        accountService.insert(acc);

        //dto.setAccount(acc);
        patientService.updateProfile(dto, "new");

        return "redirect:/patient/list";
    }
}
