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
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/account")
public class AccountsController {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public ModelAndView listUsers(ModelMap model) {
        log.info("listUsers()");
        return accountService.getListMAV();
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        log.info("newUser()");
        String loggedinuser = sessionAuth.getUserName();

        AccountDTO account = new AccountDTO();
        if (!model.containsAttribute("account")) {
            model.addAttribute("account", account);
            model.addAttribute("roles", RoleType.getRoleTypesList());
            model.addAttribute("loggedinuser", loggedinuser);
        }

        return "account/registration";
    }

    @PostMapping("/new")
    public String saveAccount(@Valid AccountDTO account, BindingResult result, ModelMap model,
                              RedirectAttributes redirectAttributes) {
        log.info("saveAccount()");
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("account", account);
        redirectAttributes.addFlashAttribute("roles", RoleType.getRoleTypesList());
        redirectAttributes.addFlashAttribute("loggedinuser", sessionAuth.getUserName());

        // TODO validate unique datas
        if (!accountService.isLoginUnique(account.getLogin())) {
            log.info("non Unique Login");
            FieldError loginErr = new FieldError("account", "login",
                    messageSource.getMessage("non.unique.login", new String[] { account.getLogin() }, Locale.ENGLISH));
            result.addError(loginErr);

            return "redirect:/account/new";
        }

        if (result.hasErrors()) {
            log.info("hasErrors()");
            return "redirect:/account/new";
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.insert(account);

        return "account/list";
    }

    @GetMapping("/delete/{login}")
    public String deleteUser(@PathVariable String login) {
        log.info("deleteUser()");
        accountService.deleteByLogin(login);
        return "redirect:/account/list";
    }

    @GetMapping("/edit/{login}")
    public String editUser(@PathVariable String login, ModelMap model) {
        log.info("editUser()");
        String loggedinuser = sessionAuth.getUserName();
        AccountDTO account = accountService.getByLogin(login);
        model.addAttribute("account", account);
        model.addAttribute("roles", RoleType.getRoleTypesList());
        model.addAttribute("loggedinuser", loggedinuser);
        return "account/registration";
    }

    @PostMapping("/edit/{login}")
    public String updateUser(@Valid AccountDTO account, BindingResult result, ModelMap model,
                             @PathVariable String login) {
        log.info("updateUser()");
        if (result.hasErrors()) {
            return "account/registration";
        }
        accountService.update(account, login);
        return "account/list";
    }
}
