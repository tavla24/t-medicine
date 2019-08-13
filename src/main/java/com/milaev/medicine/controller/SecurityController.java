package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@SessionAttributes("XXXX")
public class SecurityController {

    private static Logger log = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @RequestMapping("/")
    public String index(Model model) {
        if (!sessionAuth.isAnonimusSession()) {
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "index2";
    }

    @RequestMapping("/login") // , method = RequestMethod.GET
    public String loginPage() {
        log.info("loginPage()");
        if (sessionAuth.isAnonimusSession()) {
            log.info("return \"login\"");
            return "security/login";
        } else {
            log.info("return \"redirect:/access_granted\"");
            return "redirect:/access_granted";
        }
    }

    @RequestMapping("/access_granted") // , method = RequestMethod.GET
    public String accessGrantedPage(ModelMap model) {
        log.info("accessGrantedPage()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "security/access_granted";
    }

    @RequestMapping("/access_denied") // , method = RequestMethod.GET
    public String accessDeniedPage(ModelMap model) {
        log.info("accessDeniedPage()");
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "security/access_denied";
    }

    @RequestMapping("/logout")
    public String logoutPage() {
        log.info("logoutPage()");
        return "security/logout";
    }

//    @Autowired
//    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
//
//    @RequestMapping(value = "/logout") // , method = RequestMethod.GET
//    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth != null) {
//            persistentTokenBasedRememberMeServices.logout(request, response, auth);
//            SecurityContextHolder.getContext().setAuthentication(null);
//        }
//        return "redirect:/login";
//    }

    // @RequestMapping("/register")
    // @RequestMapping("/doctor_view")
    // @RequestMapping("/nurce_view")
    // @RequestMapping("/patient_view")
    // @RequestMapping("/error/404/403")
}
