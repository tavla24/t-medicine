package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.exceptions.AccountValidationException;
import com.milaev.medicine.service.exceptions.NullResultFromDBException;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import com.milaev.medicine.utils.converters.AccountConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService extends AbstractService implements AccountServiceInterface {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDAO daoAccount;

    @Autowired
    private RoleDAO daoRole;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ModelAndView mavList() {
        log.info("called AccountService.mavAccountsList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("dto", getAll());
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavNew() {
        log.info("called AccountService.mavNewAccount");
        ModelAndView mav = getPreparedMAV();

        mav.addObject("dto", new AccountDTO());

        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavNew(AccountDTO dto, BindingResult result) {
        log.info("called AccountService.mavNewAccount with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);

        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        insert(dto);

        return PageURLContext.getPageRedirect(mav, URI_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavDelete(String login) {
        log.info("called AccountService.mavDeleteAccount()");
        deleteByLogin(login);
        return PageURLContext.getPageRedirect(new ModelAndView(), URI_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(String login) {
        log.info("called AccountService.mavEditAccount");
        ModelAndView mav = getPreparedMAV();

        AccountDTO dto = getByLogin(login);
        dto.setEdit(true);
        mav.addObject("dto", dto);

        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(AccountDTO dto, BindingResult result, String login) {
        log.info("called AccountService.mavEditAccount with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);

        update(dto, login);

        return PageURLContext.getPageRedirect(mav, URI_LIST);
    }

    private void checkDTO(AccountDTO dto, BindingResult result,
                          ModelAndView mav) {
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new AccountValidationException(dto, result, mav);
        }
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
        return fillDTO(getEntityByLogin(login));
    }


    private Account getEntityByLogin(String login) {
        Account db = daoAccount.getByLogin(login);
        if (db == null)
            throw new NullResultFromDBException();
        return db;
    }

    @Override
    @Transactional
    public AccountDTO getById(Long id) {
        log.info("called AccountService.getById for id {}", id);
        Account db = daoAccount.getById(id);
        return fillDTO(db);
    }

    @Override
    @Transactional
    public boolean isLoginUnique(String login) {
        log.info("called AccountService.isLoginUnique for login {}", login);
        Account db = daoAccount.getByLogin(login);
        return db == null;
    }

    @Override
    @Transactional
    public void deleteByLogin(String login) {
        log.info("called AccountService.deleteByLogin for login {}", login);
        try {
            daoAccount.delete(getEntityByLogin(login));
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void update(AccountDTO dto, String oldLogin) {
        log.info("called AccountService.update for login {}", oldLogin);
        Account db = getEntityByLogin(oldLogin);
        Role role = getOrInsert(dto.getRole().getType());
        if (!db.getPassword().equals(dto.getPassword()))
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        db.setRole(role);
        AccountConverter.toEntity(dto, db);
        try {
            daoAccount.update(db);
        } catch (Exception ex) {
            report(ex);
        }
    }

    @Override
    @Transactional
    public void insert(AccountDTO dto) {
        log.info("called AccountService.update with dto");
        Account db = new Account();
        Role role = getOrInsert(dto.getRole().getType());
        db.setRole(role);
        AccountConverter.toEntity(dto, db);
        try {
            daoAccount.insert(db);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private AccountDTO fillDTO(Account db) {
        log.info("called AccountService.fillDTO with db");
        if (db != null) {
            AccountDTO dto = AccountConverter.toDTO(db);
            return dto;
        }
        return null;
    }

    private Role getOrInsert(String type) {
        log.info("called AccountService.getOrInsert for type {}", type);
        Role db = daoRole.getByType(type);

        if (db == null) {
            db = new Role();
            db.setType(type);
            daoRole.insert(db);
        }
        return db;
    }

    private void report(Exception ex) {
        log.error("Exception from Service during DB query");
        ex.printStackTrace();
        throw new NullResultFromDBException();
    }
}
