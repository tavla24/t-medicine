package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.message.MessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    private static Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private MessageSender messageSender;

    @GetMapping("/")
    public String index(Model model) {
        //messageSender.sendMessage("hello from Spring");
        if (!sessionAuth.isAnonymousSession()) {
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        log.info("loginPage()");
        if (sessionAuth.isAnonymousSession()) {
            log.info("return \"login\"");
            return "security/login";
        } else {
            log.info("return \"redirect:/access_granted\"");
            return "redirect:/access_granted";
        }
    }

    @GetMapping("/access_granted")
    public String accessGrantedPage(ModelMap model) {
        log.info("accessGrantedPage()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "security/access_granted";
    }

    @GetMapping("/access_denied")
    public String accessDeniedPage(ModelMap model) {
        log.info("accessDeniedPage()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "security/access_denied";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        log.info("logoutPage()");
        return "security/logout";
    }
}
