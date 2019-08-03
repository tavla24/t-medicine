package com.milaev.medicine.service;

import java.util.ArrayList;
import java.util.List;

import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;

@Service
public class AccountService implements AccountServiceInterface {

    private static Logger log = LoggerFactory.getLogger(AccountService.class);

    // TODO in official docs created by autowired
    // @Autowired
    // private ModelMapper modelMapper;

    @Autowired
    private AccountDAO daoAccount;

    @Autowired
    private RoleDAO daoRole;

    @Override
    @Transactional
    public List<AccountDTO> getAll() {

        List<Account> list = daoAccount.allAccounts();
        List<AccountDTO> listDAO = new ArrayList<>();
        for (Account item : list) {
            AccountDTO accountDTO = new AccountDTO();
            MapperUtil.toDTOAccount().accept(item, accountDTO);
            listDAO.add(accountDTO);
        }

        return listDAO;
    }

    @Override
    @Transactional
    public AccountDTO getByLogin(String login) {
        Account dbAccount = daoAccount.getByLogin(login);
        AccountDTO accountDTO = new AccountDTO();
        MapperUtil.toDTOAccount().accept(dbAccount, accountDTO);
        return accountDTO;
    }

    @Override
    @Transactional
    public AccountDTO getById(int id) {
        Account dbAccount = daoAccount.getById(id);
        AccountDTO accountDTO = new AccountDTO();
        MapperUtil.toDTOAccount().accept(dbAccount, accountDTO);
        return accountDTO;
    }

    @Override
    @Transactional
    public boolean isLoginUnique(String login) {
        log.info("service.isLoginUnique for login {}", login);
        try {
            // TODO is it normal way, or realize by criteria without exceptions
            getByLogin(login);
        } catch (Exception ex) {
            log.info("unique by NoResultException");
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean deleteByLogin(String login) {
        Account dbAccount = daoAccount.getByLogin(login);
        try {
            daoAccount.delete(dbAccount);
        } catch (Exception ex) {
            log.info("something wrong with delete");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean edit(AccountDTO dto, String oldLogin) {
        log.info("service.edit(Account)");
        Account dbAccount = daoAccount.getByLogin(oldLogin);
        Role r = daoRole.getByType(dto.getRole().getType());
        dbAccount.setRole(r);
        MapperUtil.toEntityAccount().accept(dto, dbAccount);
        try {
            daoAccount.edit(dbAccount);
        } catch (Exception ex) {
            log.info("something wrong with edit");
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean add(AccountDTO dto) {
        log.info("service.add(Account)");
        log.info(dto.toString());
        Role r = daoRole.getByType(dto.getRole().getType());
        Account dbAccount = new Account();
        // TODO question for Anatoliy - why cant be new Role()
        // e.t. exception "role_id" cannot be null
        dbAccount.setRole(r);
        log.info(dbAccount.toString());
        MapperUtil.toEntityAccount().accept(dto, dbAccount);
        try {
            daoAccount.add(dbAccount);
        } catch (Exception ex) {
            log.info("something wrong with insert");
            return false;
        }
        return true;
    }

}
