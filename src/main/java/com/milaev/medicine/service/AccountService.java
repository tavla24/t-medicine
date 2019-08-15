package com.milaev.medicine.service;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.validators.AccountValidator;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.model.enums.RoleType;
import com.milaev.medicine.service.exceptions.AccountValidationException;
import com.milaev.medicine.service.exceptions.DTOValidationException;
import com.milaev.medicine.service.exceptions.NullResultFromDBException;
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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Override
    @Transactional
    public ModelAndView mavAccountsList() {
        log.info("called AccountService.mavAccountsList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("accounts", getAll());
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavNewAccount(ModelMap model) {
        log.info("called AccountService.mavNewAccount");
        ModelAndView mav = getPreparedMAV();

        if (!model.containsAttribute("account")) {
            mav.addObject("account", new AccountDTO());
        } else
            mav.addAllObjects(model);

        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavNewAccount(@Valid AccountDTO account, BindingResult result,
                                      RedirectAttributes redirectAttributes) {
        log.info("called AccountService.mavNewAccount with dto");
        ModelAndView mav = getPreparedMAV();

        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new AccountValidationException(account, result, mav);
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        insert(account);

        return PageURLContext.getPageRedirect(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavDeleteAccount(String login) {
        log.info("called AccountService.mavDeleteAccount()");
        deleteByLogin(login);

        return PageURLContext.getPageRedirect(new ModelAndView(), PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEditAccount(String login) {
        log.info("called AccountService.mavEditAccount");
        ModelAndView mav = getPreparedMAV();

        AccountDTO account = getByLogin(login);
        if (account == null)
            throw new NullResultFromDBException();
        mav.addObject("account", account);

        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEditAccount(AccountDTO account, BindingResult result, String login) {
        log.info("called AccountService.mavEditAccount with dto");
        if (result.hasErrors()) {
            return PageURLContext.getPage(new ModelAndView(), PAGE_REGISTRATION);
        }
        update(account, login);

        return PageURLContext.getPageRedirect(new ModelAndView(), PAGE_LIST);
    }

    private ModelAndView getPreparedMAV() {
        log.info("called AccountService.getPreparedMAV");
        String loggedinuser = sessionAuth.getUserName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("loggedinuser", loggedinuser);
        return mav;
    }

    @Override
    @Transactional
    public List<AccountDTO> getAll() {
        log.info("called AccountService.getAll");
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
        log.info("called AccountService.getByLogin for login {}", login);
        Account dbAccount = daoAccount.getByLogin(login);
        return fillDTO(dbAccount);
    }

    @Override
    @Transactional
    public AccountDTO getById(Long id) {
        log.info("called AccountService.getById for id {}", id);
        Account dbAccount = daoAccount.getById(id);
        return fillDTO(dbAccount);
    }

    @Override
    @Transactional
    public boolean isLoginUnique(String login) {
        log.info("called AccountService.isLoginUnique for login {}", login);
        if (getByLogin(login) == null) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteByLogin(String login) {
        log.info("called AccountService.deleteByLogin for login {}", login);
        try {
            Account dbAccount = daoAccount.getByLogin(login);
            daoAccount.delete(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void update(AccountDTO dto, String oldLogin) {
        log.info("called AccountService.update for login {}", oldLogin);
        try {
            Account dbAccount = daoAccount.getByLogin(oldLogin);
            Role r = getOrInsert(dto.getRole().getType());
            if (!dbAccount.getPassword().equals(dto.getPassword()))
                dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            dbAccount.setRole(r);
            MapperUtil.toEntityAccount().accept(dto, dbAccount);
            daoAccount.update(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void insert(AccountDTO dto) {
        log.info("called AccountService.update with dto");
        try {
            Account dbAccount = new Account();
            Role r = getOrInsert(dto.getRole().getType());
            dbAccount.setRole(r);
            MapperUtil.toEntityAccount().accept(dto, dbAccount);
            daoAccount.insert(dbAccount);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private AccountDTO fillDTO(Account db) {
        log.info("called AccountService.fillDTO with db");
        if (db != null) {
            AccountDTO dto = new AccountDTO();
            MapperUtil.toDTOAccount().accept(db, dto);
            return dto;
        }
        return null;
    }

    private Role getOrInsert(String type) {
        log.info("called AccountService.getOrInsert for type {}", type);
        Role r = daoRole.getByType(type);
        if (r == null) {
            r = new Role();
            r.setType(type);
            daoRole.insert(r);
        }
        return r;
    }

    private void report(Exception ex) {
        log.error("Exception from Service during DB query");
        ex.printStackTrace();
        throw new NullResultFromDBException();
    }
}
