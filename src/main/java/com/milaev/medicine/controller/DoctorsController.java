package com.milaev.medicine.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;

@Controller
@RequestMapping("/person")
public class DoctorsController {

    private static Logger log = LoggerFactory.getLogger(DoctorsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;
    @Autowired
    MessageSource messageSource;

    @Autowired
    DoctorServiceInterface doctorService;

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public String listDoctors(ModelMap model) {
        log.info("listDoctors()");
        List<DoctorDTO> doctors = doctorService.getAll();
        model.addAttribute("doctors", doctors);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "doctor/doctorslist";
    }

    @GetMapping(value = { "/newdoctor" })
    public String newDoctor(ModelMap model) {
        log.info("newDoctor()");
        if (!model.containsAttribute("doctor")) {
            DoctorDTO doctorDTO = new DoctorDTO();
            doctorDTO.setAccount(new AccountDTO());
            doctorDTO.getAccount().setLogin(sessionAuth.getUserName());
            model.addAttribute("doctor", doctorDTO);
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "doctor/registration";
    }

    @PostMapping(value = { "/newdoctor" })
    public String saveDoctor(@Valid DoctorDTO doctorDTO, BindingResult result, ModelMap model,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("doctor", doctorDTO);
        redirectAttributes.addFlashAttribute("loggedinuser", sessionAuth.getUserName());
        log.info("saveDoctor()");

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            return "redirect:/person/newdoctor";
        }

        log.info("no validation errors");

        log.info(doctorDTO.toString());

        doctorService.add(doctorDTO);

        return "doctor/doctorslist";
    }
}
