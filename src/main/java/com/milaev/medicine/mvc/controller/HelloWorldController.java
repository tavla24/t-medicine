package com.milaev.medicine.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.milaev.medicine.mvc.model.accounts.Account;
import com.milaev.medicine.service.AccountService;

@Controller
public class HelloWorldController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/h")
    public String hello(Model model) {
        Account acc = accountService.getById(0);
        System.out.println(acc.toString());
        model.addAttribute("greeting", "Hello Spring MVC by controller");
        System.out.println("/hellosys");
        return "helloworld";
    }
}