package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.service.ArticleService;
import com.milaev.medicine.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
public class SecurityController {

    private static Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private ArticleService articleService;

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String index(Model model) {
        if (!sessionAuth.isAnonymousSession()) {
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        model.addAttribute("dto", articleService.getAll());
        return "index2";
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
