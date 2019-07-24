package com.milaev.medicine.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milaev.medicine.db.entity.Account;
import com.milaev.medicine.db.entity.AccountSimple;
import com.milaev.medicine.service.interfaces.TServiceInterface;

import java.util.List;

import org.apache.log4j.Logger;

@Controller
public class HelloWorldController {
    private static Logger LOGGER = Logger.getLogger(HelloWorldController.class);
    // TODO only interfaces? why?
    @Autowired
    private TServiceInterface<Account> accountService;
    @Autowired
    private TServiceInterface<AccountSimple> accountSimpleService;

    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/o")
    public String hello(Model model) {
        LOGGER.info("hello started");
        AccountSimple acc = accountSimpleService.getById(2);
        LOGGER.info("accountSimpleService.getById(X) done");
        LOGGER.info(acc.toString());
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        LOGGER.info("/hellosys");
        return "helloworld";
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

    @RequestMapping("/a")
    public String add(Model model) {
        AccountSimple as = new AccountSimple();
        as.setId(0);
        as.setName("kesha");
        LOGGER.info("hello started");
        accountSimpleService.add(as);
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        
        List<AccountSimple> las = accountSimpleService.allAccounts();
        for (AccountSimple item: las) {
            LOGGER.info(item.toString());
        }
        
        LOGGER.info("/hellosys");
        return "helloworld";
    }

    @RequestMapping("/d")
    public String del(Model model) {
        AccountSimple as = new AccountSimple();
        as.setId(0);
        as.setName("kesha");
        LOGGER.info("hello started");
        accountSimpleService.delete(as);
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        LOGGER.info("/hellosys");
        
        List<AccountSimple> las = accountSimpleService.allAccounts();
        for (AccountSimple item: las) {
            LOGGER.info(item.toString());
        }
        
        return "helloworld";
    }
}