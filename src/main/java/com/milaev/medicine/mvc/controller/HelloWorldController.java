package com.milaev.medicine.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milaev.medicine.mvc.model.accounts.AccountSimple;
import com.milaev.medicine.service.interfaces.AccountSimpleServiceInterface;

@Controller
public class HelloWorldController {
    @Autowired
    private AccountSimpleServiceInterface accountService;
    
    @RequestMapping("/")
    public String index(Model model) {
        return "index";
    }

    @RequestMapping("/h")
    public String hello(Model model) {
        System.out.println("hello started");
        AccountSimple acc = accountService.getById(2);
        System.out.println("accountService.getById(X) done");
        System.out.println(acc.toString());
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        System.out.println("/hellosys");
        return "helloworld";
    }
}