package com.milaev.medicine.service;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class AccountService implements AccountServiceInterface {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDAO daoAccount;

    @Autowired
    private RoleDAO daoRole;

    @Autowired
    private AccountServiceInterface accountService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    private static final String PAGE_LIST = "account/list";
    private static final String PAGE_REGISTRATION = "account/registration";
    private static final String PAGE_NEW = "account/new";

    @Override
    @Transactional
    public ModelAndView mavAccountsList(){
        log.info("mavAccountsList()");
        ModelAndView mav = getPreparedMAV();
        List<AccountDTO> accounts = getAll();
        mav.addObject("accounts", accounts);
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavNewAccount(ModelMap model) {
        log.info("mavNewAccount()");
        ModelAndView mav = getPreparedMAV();

        AccountDTO account = new AccountDTO();
        if (!model.containsAttribute("account")) {
            mav.addObject("account", account);
        } else
            mav.addAllObjects(model);
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavNewAccount(AccountDTO account, BindingResult result,
                              RedirectAttributes redirectAttributes) {
        log.info("mavNewAccount()");
        ModelAndView mav = getPreparedMAV();

        redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.account", result);
        redirectAttributes.addFlashAttribute("account", account);

        // TODO validate unique datas
        if (!accountService.isLoginUnique(account.getLogin())) {
            log.info("non Unique Login");
            FieldError loginErr = new FieldError("account", "login",
                    messageSource.getMessage("non.unique.login", new String[] { account.getLogin() }, Locale.ENGLISH));
            result.addError(loginErr);
            log.info(result.getAllErrors().toString());
            //RedirectView redirectView = new RedirectView("/admin/account/new");
            //redirectView.setContextRelative(true);
            //mav.setView(redirectView);
            return PageURLContext.getPageRedirect(mav, PAGE_NEW);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        accountService.insert(account);

        return PageURLContext.getPageRedirect(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavDeleteAccount(String login) {
        log.info("mavDeleteAccount()");
        accountService.deleteByLogin(login);
        return PageURLContext.getPageRedirect(new ModelAndView(), PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEditAccount(String login) {
        log.info("mavEditAccount()");
        ModelAndView mav = getPreparedMAV();

        AccountDTO account = accountService.getByLogin(login);
        mav.addObject("account", account);
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEditAccount(AccountDTO account, BindingResult result, String login) {
        log.info("mavEditAccount()");
        if (result.hasErrors()) {
            return PageURLContext.getPage(new ModelAndView(), PAGE_REGISTRATION);
        }
        accountService.update(account, login);
        return PageURLContext.getPage(new ModelAndView(), PAGE_LIST);
    }

    private ModelAndView getPreparedMAV(){
        String loggedinuser = sessionAuth.getUserName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("loggedinuser", loggedinuser);
        return mav;
    }

    @Override
    @Transactional
    public List<AccountDTO> getAll() {
        List<Account> list = daoAccount.getAll();
        List<AccountDTO> listDAO = new ArrayList<>();
        for (Account item : list) {
            listDAO.add(fillDTO(item));
        }
        return listDAO;
    }

    @Override
    @Transactional
    public AccountDTO getByLogin(String login) {
        Account dbAccount = daoAccount.getByLogin(login);
        return fillDTO(dbAccount);
    }

    @Override
    @Transactional
    public AccountDTO getById(Long id) {
        Account dbAccount = daoAccount.getById(id);
        return fillDTO(dbAccount);
    }

    @Override
    @Transactional
    public boolean isLoginUnique(String login) {
        log.info("service.isLoginUnique for login {}", login);
        if (getByLogin(login) == null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteByLogin(String login) {
        Account dbAccount = daoAccount.getByLogin(login);
        try {
            daoAccount.delete(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void update(AccountDTO dto, String oldLogin) {
        log.info("service.update(Account)");
        Account dbAccount = daoAccount.getByLogin(oldLogin);
        Role r = getOrInsert(dto.getRole().getType());
        dbAccount.setRole(r);
        MapperUtil.toEntityAccount().accept(dto, dbAccount);
        try {
            daoAccount.update(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void insert(AccountDTO dto) {
        log.info("service.insert(Account)");
        Account dbAccount = new Account();
        Role r = getOrInsert(dto.getRole().getType());
        dbAccount.setRole(r);
        log.info(dbAccount.toString());
        MapperUtil.toEntityAccount().accept(dto, dbAccount);
        try {
            daoAccount.insert(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private AccountDTO fillDTO(Account db) {
        if (db != null) {
            AccountDTO dto = new AccountDTO();
            MapperUtil.toDTOAccount().accept(db, dto);
            return dto;
        }
        return null;
    }

    private Role getOrInsert(String type){
        Role r = daoRole.getByType(type);
        if(r == null) {
            r = new Role();
            r.setType(type);
            daoRole.insert(r);
        }
        return r;
    }

    private void report(Exception ex) {
        log.error("Exception from Service during DB query");
        ex.printStackTrace();
    }
}
