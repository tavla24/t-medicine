package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.service.PersonService;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@RequestMapping("/admin/doctor")
public class AdminDoctorsController {

    private static Logger log = LoggerFactory.getLogger(AdminDoctorsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    MessageSource messageSource;

    @Autowired
    //@Qualifier("doctorServiceN")
    @Qualifier("doctorService")
    DoctorServiceInterface doctorService;

    @GetMapping(value = "/") // , method = RequestMethod.GET
    public String main(ModelMap model) {
        log.info("main()");
        return "doctor/mainpanel";
    }

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public String listDoctors(ModelMap model) {
        log.info("listDoctors()");
        List<DoctorDTO> doctors = doctorService.getAll();
        model.addAttribute("doctors", doctors);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "doctor/list";
    }

    @GetMapping(value = {"/new"})
    public String newDoctor(ModelMap model) {
        log.info("newDoctor()");
        if (!model.containsAttribute("doctor")) {
            DoctorDTO doctorDTO = new DoctorDTO();
            model.addAttribute("doctor", doctorDTO);
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "doctor/registration";
    }

    @PostMapping(value = {"/new"})
    public String saveDoctor(@Valid DoctorDTO doctorDTO, BindingResult result, ModelMap model,
                             RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("doctor", doctorDTO);
        redirectAttributes.addFlashAttribute("loggedinuser", sessionAuth.getUserName());
        log.info("saveDoctor()");

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            return "redirect:/admin/doctor/new";
        }

        log.info("no validation errors");

        doctorService.updateProfile(doctorDTO);

        return "redirect:/admin/doctor/list";
    }

    // @DeleteMapping(value = { "/delete-user-{login}" })
    @GetMapping(value = { "/delete/{login}" })
    public String deleteDoctor(@PathVariable String login) {
        log.info("deleteDoctor()");
        doctorService.deleteProfile(login);
        return "redirect:/admin/doctor/list";
    }

    @GetMapping(value = { "/edit/{login}" })
    public String editDoctor(@PathVariable String login, ModelMap model) {
        log.info("editDoctor()");
        DoctorDTO doctor = doctorService.getByLogin(login);
        model.addAttribute("doctor", doctor);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "doctor/registration";
    }

    @PostMapping("/edit/{login}")
    public String updateDoctor(@Valid DoctorDTO doctorDTO, BindingResult result, ModelMap model,
                               @PathVariable String login) {
        log.info("updateDoctor()");
        if (result.hasErrors()) {
            return "doctor/registration";
        }

        doctorService.updateProfile(doctorDTO);

        return "redirect:/admin/doctor/list";
    }
}
