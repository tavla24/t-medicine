package com.milaev.medicine.controller;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.validators.DoctorValidator;
import com.milaev.medicine.service.DoctorService;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DoctorsController {

    private static Logger log = LoggerFactory.getLogger(DoctorsController.class);

    @Autowired
    @Qualifier("doctorService")
    protected DoctorService doctorService;

    @Autowired
    private DoctorValidator doctorValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(doctorValidator);
    }

    @GetMapping("/admin/doctor/list")
    public ModelAndView listDoctors() {
        log.info("[/admin/doctor] get request for url /list");
        ModelAndView mav = doctorService.getPreparedMAV();
        mav.addObject("dto", doctorService.getAll());
        return PageURLContext.getPage(mav, doctorService.PAGE_LIST);
    }

    @GetMapping("/admin/doctor/new")
    public ModelAndView newDoctor() {
        log.info("[/admin/doctor] get request for url /new");
        ModelAndView mav = doctorService.getPreparedMAV();
        mav.addObject("dto", new DoctorDTO());
        return PageURLContext.getPage(mav, doctorService.PAGE_REGISTRATION);
    }

    @PostMapping("/admin/doctor/new")
    public ModelAndView newDoctor(@Validated DoctorDTO dto, BindingResult result) {
        log.info("[/admin/doctor] post request for url /new");
        ModelAndView mav = doctorService.getPreparedMAV();
        doctorService.checkDTO(dto, result, mav);
        doctorService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, doctorService.URI_LIST);
    }

    @GetMapping("/admin/doctor/delete/{login}")
    public ModelAndView deleteDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /delete/{}", login);
        doctorService.deleteProfile(login);
        return PageURLContext.getPageRedirect(new ModelAndView(), doctorService.URI_LIST);
    }

    @GetMapping("/admin/doctor/edit/{login}" )
    public ModelAndView editDoctor(@PathVariable String login) {
        log.info("[/admin/doctor] get request for url /edit/{}", login);
        return mavEdit(login);
    }

    @PostMapping("/admin/doctor/edit/{login}")
    public ModelAndView editDoctor(@Validated DoctorDTO dto, BindingResult result,
                                     @PathVariable String login) {
        log.info("[/admin/doctor] post request for url /edit/{}", login);
        ModelAndView mav = doctorService.getPreparedMAV();
        doctorService.checkDTO(dto, result, mav);
        doctorService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, doctorService.URI_MAIN);
    }

    @GetMapping("/doctor/edit")
    public ModelAndView editDoctor(ModelMap model) {
        log.info("[/doctor] get request for url /edit");
        return mavEdit("");
    }

    @PostMapping("/doctor/edit")
    public ModelAndView updateDoctor(@Validated DoctorDTO dto, BindingResult result) {
        //return "redirect:/";
        log.info("[/doctor] post request for url /edit");
        ModelAndView mav = doctorService.getPreparedMAV();
        doctorService.checkDTO(dto, result, mav);
        doctorService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, doctorService.URI_MAIN);
    }


    private ModelAndView mavEdit(String login) {
        log.info("called DoctorService.mavEdit");
        ModelAndView mav = doctorService.getPreparedMAV();

        DoctorDTO dto;
        if (login.isEmpty()){
            String loggedinuser = (String) mav.getModel().get("loggedinuser");
            if (doctorService.isProfileExist(loggedinuser))
                dto = doctorService.getByLogin(loggedinuser);
            else {
                dto = new DoctorDTO();
                dto.setLogin(loggedinuser);
            }
        } else
            dto = doctorService.getByLogin(login);

        dto.setEdit(true);
        mav.addObject("dto", dto);

        return PageURLContext.getPage(mav, doctorService.PAGE_REGISTRATION);
    }

}
