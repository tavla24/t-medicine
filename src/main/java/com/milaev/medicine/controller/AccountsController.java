package com.milaev.medicine.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.interfaces.RoleServiceInterface;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Controller
public class AccountsController {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private TServiceInterface<Account, AccountDTO> accountService;
    @Autowired
    private RoleServiceInterface roleService;

    @Autowired
    MessageSource messageSource;

    @RequestMapping(value = "/list") // , method = RequestMethod.GET
    public String listUsers(ModelMap model) {
        log.info("listUsers()");
        List<AccountDTO> users = accountService.entityToDTO(accountService.allAccounts());
        model.addAttribute("users", users);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "userslist";
    }

    @RequestMapping(value = { "/newaccount" }, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        AccountDTO account = new AccountDTO();

        model.addAttribute("account", account);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "registration";
    }

    @RequestMapping(value = { "/newaccount" }, method = RequestMethod.POST)
    public String saveAccount(@Valid AccountDTO account, BindingResult result, ModelMap model) {
        model.addAttribute("account", account);
        log.info("saveAccount()");

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            // point 1
            // return "redirect:/newaccount";
        }

        log.info("no validation errors");

        boolean update = false;
        if (!accountService.isLoginUnique(account.getLogin())) {
            log.info("non Unique Login");
            update = true;
            FieldError loginErr = new FieldError("account", "login",
                    messageSource.getMessage("non.unique.login", new String[] { account.getLogin() }, Locale.ENGLISH));
            result.addError(loginErr);
            log.info(result.getAllErrors().toString());
            // point 2
            // return "registration";
        }

        log.info("AccountDTO: ", account.toString());
        // point 3
        Account acc = accountService.dtoToEntity(account);
        Role r = roleService.getByType(acc.getRole());
        acc.setRole(r);
        log.info("AccountEntity: ", acc.toString());

        if (update) {
            // point 5
            accountService.edit(acc);
        } else
            accountService.add(acc);

        // point 4
        return "userslist";
    }
}
