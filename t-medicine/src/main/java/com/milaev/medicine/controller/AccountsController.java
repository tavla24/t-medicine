package com.milaev.medicine.controller;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/account")
public class AccountsController {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @GetMapping("/list")
    public ModelAndView listAccounts() {
        log.info("[account] get request for url /list");
        return accountService.mavList();
    }

    @GetMapping("/new")
    public ModelAndView newAccount() {
        log.info("[account] get request for url /new");
        return accountService.mavNew();
    }

    @PostMapping("/new")
    public ModelAndView newAccount(@Validated AccountDTO account, BindingResult result) {
        log.info("[account] post request for url /new");
        return accountService.mavNew(account, result);
    }

    @GetMapping("/delete/{login}")
    public ModelAndView deleteAccount(@PathVariable String login) {
        log.info("[account] get request for url /delete/{}", login);
        return accountService.mavDelete(login);
    }

    @GetMapping("/edit/{login}")
    public ModelAndView editAccount(@PathVariable String login) {
        log.info("[account] get request for url /edit/{}", login);
        return accountService.mavEdit(login);
    }

    @PostMapping("/edit/{login}")
    public ModelAndView editAccount(@Validated AccountDTO account, BindingResult result, @PathVariable String login) {
        log.info("[account] post request for url /edit/{}", login);
        return accountService.mavEdit(account, result, login);
    }
}
