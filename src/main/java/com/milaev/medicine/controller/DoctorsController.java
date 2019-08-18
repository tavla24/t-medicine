package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.validators.DoctorValidator;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/doctor")
public class DoctorsController {

    private static Logger log = LoggerFactory.getLogger(DoctorsController.class);

    @Autowired
    @Qualifier("doctorService")
    DoctorServiceInterface doctorService;

    @Autowired
    DoctorValidator doctorValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(doctorValidator);
    }

    @GetMapping(value = {"/edit"})
    public ModelAndView editDoctor(ModelMap model) {
        log.info("[/doctor] get request for url /edit");
        return doctorService.mavEdit("");
    }

    @PostMapping("/edit")
    public ModelAndView updateDoctor(@Validated DoctorDTO dto, BindingResult result) {
        //return "redirect:/";
        log.info("[/doctor] post request for url /edit");
        return doctorService.mavEdit(dto, result);
    }
}
