package com.milaev.medicine.service;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService implements AccountServiceInterface {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private AccountDAO daoAccount;

    @Autowired
    private RoleDAO daoRole;

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Override
    public ModelAndView getListMAV(){
        String loggedinuser = sessionAuth.getUserName();

        List<AccountDTO> accounts = getAll();

        ModelAndView mav = new ModelAndView();

        mav.addObject("accounts", accounts);
        mav.addObject("loggedinuser", loggedinuser);
        mav.setViewName("account/list");
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
