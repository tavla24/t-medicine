package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private static Logger log = LoggerFactory.getLogger(PatientController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;
    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/")
    public String patient(ModelMap model) {
        log.info("patient()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "patient/mainpanel";
    }
}
