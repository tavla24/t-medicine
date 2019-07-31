package com.milaev.medicine.controller;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@SessionAttributes("XXXX")
public class MainController {

    private static Logger log = LoggerFactory.getLogger(MainController.class);

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/hello")
    public String hello(Model model) {
        log.info("hello()");
        model.addAttribute("greeting", "Hello page: Spring MVC by controller");
        return "hello";
    }

    @RequestMapping("/login") // , method = RequestMethod.GET
    public String loginPage() {
        log.info("loginPage()");
        if (isCurrentAuthenticationAnonymous()) {
            log.info("return \"login\"");
            return "login";
        } else {
            log.info("return \"redirect:/access_granted\"");
            return "redirect:/access_granted";
        }
    }

    @RequestMapping("/access_granted") // , method = RequestMethod.GET
    public String accessGrantedPage(ModelMap model) {
        log.info("accessGrantedPage()");
        model.addAttribute("loggedinuser", getPrincipal());
        return "access_granted";
    }

    @RequestMapping("/access_denied") // , method = RequestMethod.GET
    public String accessDeniedPage(ModelMap model) {
        log.info("accessDeniedPage()");
        model.addAttribute("loggedinuser", getPrincipal());
        return "access_denied";
    }

    @RequestMapping("/logout")
    public String logoutPage() {
        log.info("logoutPage()");
        return "logout";
    }

//	@RequestMapping(value="/logout") //, method = RequestMethod.GET
//	public String logoutPage (HttpServletRequest request, HttpServletResponse response){
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		if (auth != null){    
//			persistentTokenBasedRememberMeServices.logout(request, response, auth);
//			SecurityContextHolder.getContext().setAuthentication(null);
//		}
//		return "redirect:/login";
//	}

//    @RequestMapping(value = "/list")//, method = RequestMethod.GET
//    public String listUsers(ModelMap model) {
//        List<User> users = userService.findAllUsers();
//        model.addAttribute("users", users);
//        model.addAttribute("loggedinuser", getPrincipal());
//        return "userslist";
//    }

    // @RequestMapping("/register")
    // @RequestMapping("/doctor_view")
    // @RequestMapping("/nurce_view")
    // @RequestMapping("/patient_view")
    // @RequestMapping("/error/404/403")

    private String getPrincipal() {
        log.info("getPrincipal()");
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            log.info("getPrincipal(): instanceof UserDetails");
            userName = ((UserDetails) principal).getUsername();
            log.info("getPrincipal() UserDetails userName: " + userName);

            Collection<GrantedAuthority> cga = ((Collection<GrantedAuthority>) ((UserDetails) principal)
                    .getAuthorities());
            Iterator<GrantedAuthority> icga = cga.iterator();
            while (icga.hasNext())
                log.info("getPrincipal() UserDetails Authority: " + icga.next().getAuthority());
        } else {
            userName = principal.toString();
            log.info("getPrincipal() principal.toString() userName: " + userName);
        }
        return userName;
    }

    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
