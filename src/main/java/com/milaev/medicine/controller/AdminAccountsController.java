package com.milaev.medicine.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import com.milaev.medicine.model.enums.RoleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.service.interfaces.RoleServiceInterface;

@Controller
@RequestMapping("/admin/account")
public class AdminAccountsController {

    private static Logger log = LoggerFactory.getLogger(AdminAccountsController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private AccountServiceInterface accountService;
    @Autowired
    private RoleServiceInterface roleService;

    @Autowired
    MessageSource messageSource;

    @GetMapping(value = "/") // , method = RequestMethod.GET
    public String main(ModelMap model) {
        log.info("main()");
        return "account/mainpanel";
    }

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public String listUsers(ModelMap model) {
        log.info("listUsers()");
        List<AccountDTO> accounts = accountService.getAll();
        model.addAttribute("accounts", accounts);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "account/list";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model) {
        log.info("newUser()");
        AccountDTO account = new AccountDTO();

        if (!model.containsAttribute("account")) {
            log.info("!model.containsAttribute(\"account\")");
            model.addAttribute("account", account);

            model.addAttribute("roles", RoleType.getRoleTypesList());
            model.addAttribute("loggedinuser", sessionAuth.getUserName());
        }
        return "account/registration";
    }

    @PostMapping("/new")
    public String saveAccount(@Valid AccountDTO account, BindingResult result, ModelMap model,
            RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("account", account);
        redirectAttributes.addFlashAttribute("loggedinuser", sessionAuth.getUserName());
        log.info("saveAccount()");

        // TODO validate unique datas
        if (!accountService.isLoginUnique(account.getLogin())) {
            log.info("non Unique Login");
            FieldError loginErr = new FieldError("account", "login",
                    messageSource.getMessage("non.unique.login", new String[] { account.getLogin() }, Locale.ENGLISH));
            result.addError(loginErr);
            log.info(result.getAllErrors().toString());
            // point 2
            return "redirect:/admin/account/new";
            // return "account/registration";
        }

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            return "redirect:/admin/account/new";
        }

        log.info("no validation errors");
        log.info("AccountDTO: ", account.toString());
        // point 3
//        Account acc = accountService.dtoToEntity(account);
//        Role r = roleService.getByType(acc.getRole());
//        acc.setRole(r);
//        log.info("AccountEntity: ", acc.toString());

        accountService.add(account);

        // point 4
        return "account/list";
    }

    // @DeleteMapping(value = { "/delete-user-{login}" })
    @GetMapping(value = { "/delete/{login}" })
    public String deleteUser(@PathVariable String login) {
        log.info("deleteUser()");
        accountService.deleteByLogin(login);
        return "redirect:/admin/account/list";
    }

    @GetMapping(value = { "/edit/{login}" })
    public String editUser(@PathVariable String login, ModelMap model) {
        log.info("editUser()");
        AccountDTO account = accountService.getByLogin(login);
        model.addAttribute("account", account);
        model.addAttribute("roles", RoleType.getRoleTypesList());
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "account/registration";
    }

    @PostMapping("/edit/{login}")
    public String updateUser(@Valid AccountDTO account, BindingResult result, ModelMap model,
            @PathVariable String login) {
        log.info("updateUser()");
        if (result.hasErrors()) {
            return "account/registration";
        }

        accountService.edit(account, login);

        return "account/list";
    }
}
