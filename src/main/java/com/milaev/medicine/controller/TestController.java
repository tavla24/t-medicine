package com.milaev.medicine.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milaev.medicine.model.Account;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Controller
public class TestController {
    private static Logger LOGGER = Logger.getLogger(TestController.class);
    // TODO only interfaces? why?
    @Autowired
    private TServiceInterface<Account> accountService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/o2")
    public String hello2(Model model) {
        LOGGER.info("hello started");
        Account acc = accountService.getById(2);
        LOGGER.info("accountService.getById(X) done");
        LOGGER.info(acc.toString());
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        LOGGER.info("/hellosys");
        return "helloworld";
    }

    @RequestMapping(value = "/l", method = RequestMethod.GET)
    public String loginPage() {
//        if (isCurrentAuthenticationAnonymous()) {
//            return "login";
//        } else {
//            return "redirect:/list";
//        }
        return "login";
    }
}