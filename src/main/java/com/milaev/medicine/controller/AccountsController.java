package com.milaev.medicine.controller;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/account")
public class AccountsController {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    private AccountValidator accountValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @GetMapping("/list")
    public ModelAndView listAccounts() {
        log.info("[account] get request for url /list");
        return accountService.mavAccountsList();
    }

    @GetMapping("/new")
    public ModelAndView newAccount(ModelMap model) {
        log.info("[account] get request for url /new");
        return accountService.mavNewAccount(model);
    }

    @PostMapping("/new")
    public ModelAndView newAccount(@Validated AccountDTO account, BindingResult result, RedirectAttributes redirectAttributes) {
        log.info("[account] post request for url /new");
        return accountService.mavNewAccount(account, result, redirectAttributes);
    }

    @GetMapping("/delete/{login}")
    public ModelAndView deleteAccount(@PathVariable String login) {
        log.info("[account] get request for url /delete/{}", login);
        return accountService.mavDeleteAccount(login);
    }

    @GetMapping("/edit/{login}")
    public ModelAndView editAccount(@PathVariable String login) {
        log.info("[account] get request for url /edit/{}", login);
        return accountService.mavEditAccount(login);
    }

    @PostMapping("/edit/{login}")
    public ModelAndView editAccount(@Validated AccountDTO account, BindingResult result, @PathVariable String login) {
        log.info("[account] post request for url /edit/{}", login);
        return accountService.mavEditAccount(account, result, login);
    }
}
