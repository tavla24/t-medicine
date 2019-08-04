package com.milaev.medicine.controller;

import java.util.List;

import javax.validation.Valid;

import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
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

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;

@Controller
@RequestMapping("/doctor")
public class DoctorsController {

    private static Logger log = LoggerFactory.getLogger(DoctorsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;
    @Autowired
    MessageSource messageSource;
    @Autowired
    DoctorServiceInterface doctorService;
    @Autowired
    AccountServiceInterface accountService;

    @GetMapping(value = "/") // , method = RequestMethod.GET
    public String main(ModelMap model) {
        log.info("main()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "doctor/mainpanel";
    }

    @GetMapping(value = {"/edit"})
    public String editDoctor(ModelMap model) {
        log.info("editDoctor()");
        String loggedinuser = sessionAuth.getUserName();

        DoctorDTO doctorDTO;
        if (doctorService.isProfileExist(loggedinuser))
        doctorDTO = doctorService.getByLogin(loggedinuser);
        else {
            doctorDTO = new DoctorDTO();
            doctorDTO.getAccount().setLogin(loggedinuser);
        }

        model.addAttribute("doctor", doctorDTO);
        model.addAttribute("loggedinuser", loggedinuser);
        return "doctor/registration";
    }

    @PostMapping("/edit")
    public String updateDoctor(@Valid DoctorDTO doctorDTO, BindingResult result, ModelMap model) {
        log.info("updateDoctor()");
        if (result.hasErrors()) {
            return "doctor/registration";
        }
        String loggedinuser = sessionAuth.getUserName();

        //log.info(sessionAuth.getUserName());
        //log.info(doctorDTO.toString());
        //doctorService.insert(doctorDTO);//, sessionAuth.getUserName()

        doctorDTO.getAccount().setLogin(loggedinuser);
        doctorService.updateProfile(doctorDTO);

        return "redirect:/doctor/";
    }
}
