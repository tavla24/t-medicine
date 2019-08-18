package com.milaev.medicine.controller;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.validators.DoctorValidator;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorsController {

    private static Logger log = LoggerFactory.getLogger(DoctorsController.class);

    @Autowired
    @Qualifier("doctorService")
    private DoctorServiceInterface doctorService;

    @Autowired
    private DoctorValidator doctorValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(doctorValidator);
    }

    @GetMapping("/admin/doctor/list")
    public ModelAndView listDoctors() {
        log.info("[/admin/doctor] get request for url /list");
        return doctorService.mavList();
    }

    @GetMapping("/admin/doctor/new")
    public ModelAndView newDoctor() {
        log.info("[/admin/doctor] get request for url /new");
        return doctorService.mavNew();
    }

    @PostMapping("/admin/doctor/new")
    public ModelAndView newDoctor(@Validated DoctorDTO dto, BindingResult result) {
        log.info("[/admin/doctor] post request for url /new");
        return doctorService.mavNew(dto, result);
    }

    @GetMapping("/admin/doctor/delete/{login}")
    public ModelAndView deleteDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /delete/{}", login);
        return doctorService.mavDelete(login);
    }

    @GetMapping("/admin/doctor/edit/{login}" )
    public ModelAndView editDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /edit/{}", login);
        return doctorService.mavEdit(login);
    }

    @PostMapping("/admin/doctor/edit/{login}")
    public ModelAndView editDoctor(@Validated DoctorDTO dto, BindingResult result,
                                     @PathVariable String login) {
        log.info("[/admin/doctor] post request for url /edit/{}", login);
        return doctorService.mavEdit(dto, result);
    }

    @GetMapping("/doctor/edit")
    public ModelAndView editDoctor(ModelMap model) {
        log.info("[/doctor] get request for url /edit");
        return doctorService.mavEdit("");
    }

    @PostMapping("/doctor/edit")
    public ModelAndView updateDoctor(@Validated DoctorDTO dto, BindingResult result) {
        //return "redirect:/";
        log.info("[/doctor] post request for url /edit");
        return doctorService.mavEdit(dto, result);
    }
}
