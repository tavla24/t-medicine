package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.validators.DoctorValidator;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/doctor")
public class AdminDoctorsController {

    private static Logger log = LoggerFactory.getLogger(AdminDoctorsController.class);

    @Autowired
    @Qualifier("doctorService")
    private DoctorServiceInterface doctorService;

    @Autowired
    private DoctorValidator doctorValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(doctorValidator);
    }

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public ModelAndView listDoctors() {
        log.info("[/admin/doctor] get request for url /list");
        return doctorService.mavList();
    }

    @GetMapping(value = {"/new"})
    public ModelAndView newDoctor() {
        log.info("[/admin/doctor] get request for url /new");
        return doctorService.mavNew();
    }

    @PostMapping(value = {"/new"})
    public ModelAndView newDoctor(@Validated DoctorDTO dto, BindingResult result) {
        log.info("[/admin/doctor] post request for url /new");
        return doctorService.mavNew(dto, result);
    }

    @GetMapping(value = { "/delete/{login}" })
    public ModelAndView deleteDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /delete/{}", login);
        return doctorService.mavDelete(login);
    }

    @GetMapping(value = { "/edit/{login}" })
    public ModelAndView editDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /edit/{}", login);
        return doctorService.mavEdit(login);
    }

    @PostMapping("/edit/{login}")
    public ModelAndView editDoctor(@Validated DoctorDTO dto, BindingResult result,
                                     @PathVariable String login) {
        log.info("[/admin/doctor] post request for url /edit/{}", login);
        return doctorService.mavEdit(dto, result);
    }
}
