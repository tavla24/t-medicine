package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Locale;

@Controller
@RequestMapping("/account")
public class AccountsController {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private AccountServiceInterface accountService;

    @GetMapping("/list")
    public ModelAndView listAccounts() {
        log.info("listAccounts()");
        return accountService.mavAccountsList();
    }

    @GetMapping("/new")
    public ModelAndView newAccount(ModelMap model) {
        log.info("newAccount()");
        return accountService.mavNewAccount(model);
    }

    @PostMapping("/new")
    public ModelAndView newAccount(@Valid AccountDTO account, BindingResult result, RedirectAttributes redirectAttributes) {
        log.info("saveAccount()");
        return accountService.mavNewAccount(account, result, redirectAttributes);
    }

    @GetMapping("/delete/{login}")
    public ModelAndView deleteAccount(@PathVariable String login) {
        log.info("deleteAccount()");
        return accountService.mavDeleteAccount(login);
    }

    @GetMapping("/edit/{login}")
    public ModelAndView editAccount(@PathVariable String login) {
        log.info("editAccount()");
        return accountService.mavEditAccount(login);
    }

    @PostMapping("/edit/{login}")
    public ModelAndView editAccount(@Valid AccountDTO account, BindingResult result, @PathVariable String login) {
        log.info("editAccount()");
        return accountService.mavEditAccount(account, result, login);
    }
}
