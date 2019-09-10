package com.milaev.medicine.controller;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.service.AccountService;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(accountValidator);
    }

    @GetMapping("/list")
    public ModelAndView listAccounts() {
        log.info("[account] get request for url /list");
        return mavList();
    }

    @GetMapping("/new")
    public ModelAndView newAccount() {
        log.info("[account] get request for url /new");
        return mavNew();
    }

    @PostMapping("/new")
    public ModelAndView newAccount(@Validated AccountDTO account, BindingResult result) {
        log.info("[account] post request for url /new");
        return mavNew(account, result);
    }

    @GetMapping("/delete/{login}")
    public ModelAndView deleteAccount(@PathVariable String login) {
        log.info("[account] get request for url /delete/{}", login);
        return mavDelete(login);
    }

    @GetMapping("/edit/{login}")
    public ModelAndView editAccount(@PathVariable String login) {
        log.info("[account] get request for url /edit/{}", login);
        return mavEdit(login);
    }

    @PostMapping("/edit/{login}")
    public ModelAndView editAccount(@Validated AccountDTO account, BindingResult result, @PathVariable String login) {
        log.info("[account] post request for url /edit/{}", login);
        return mavEdit(account, result, login);
    }

    public ModelAndView mavList() {
        log.info("called AccountService.mavAccountsList");
        ModelAndView mav = accountService.getPreparedMAV();
        mav.addObject("dto", accountService.getAll());
        return PageURLContext.getPage(mav, accountService.PAGE_LIST);
    }

    public ModelAndView mavNew() {
        log.info("called AccountService.mavNewAccount");
        ModelAndView mav = accountService.getPreparedMAV();

        mav.addObject("dto", new AccountDTO());

        return PageURLContext.getPage(mav, accountService.PAGE_REGISTRATION);
    }

    public ModelAndView mavNew(AccountDTO dto, BindingResult result) {
        log.info("called AccountService.mavNewAccount with dto");
        ModelAndView mav = accountService.getPreparedMAV();
        accountService.checkDTO(dto, result, mav);

//        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        accountService.insert(dto);

        return PageURLContext.getPageRedirect(mav, accountService.URI_LIST);
    }

    public ModelAndView mavDelete(String login) {
        log.info("called AccountService.mavDeleteAccount()");
        accountService.deleteByLogin(login);
        return PageURLContext.getPageRedirect(new ModelAndView(), accountService.URI_LIST);
    }

    public ModelAndView mavEdit(String login) {
        log.info("called AccountService.mavEditAccount");
        ModelAndView mav = accountService.getPreparedMAV();

        AccountDTO dto = accountService.getByLogin(login);
        dto.setEdit(true);
        mav.addObject("dto", dto);

        return PageURLContext.getPage(mav, accountService.PAGE_REGISTRATION);
    }

    public ModelAndView mavEdit(AccountDTO dto, BindingResult result, String login) {
        log.info("called AccountService.mavEditAccount with dto");
        ModelAndView mav = accountService.getPreparedMAV();
        accountService.checkDTO(dto, result, mav);

        accountService.update(dto, login);

        return PageURLContext.getPageRedirect(mav, accountService.URI_LIST);
    }

}
