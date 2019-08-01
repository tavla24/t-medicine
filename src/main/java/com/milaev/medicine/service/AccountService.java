package com.milaev.medicine.service;

import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.NoResultException;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.controller.AccountsController;
import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Service
public class AccountService implements TServiceInterface<Account, AccountDTO> {

    private static Logger log = LoggerFactory.getLogger(AccountsController.class);

    // TODO in official docs created by autowired
    // @Autowired
    // private ModelMapper modelMapper;

    @Autowired
    private AccountDAO dao;

    @Override
    @Transactional
    public List<Account> allAccounts() {
        return dao.allAccounts();
    }

    @Override
    @Transactional
    public void add(Account acc) {
        log.info("service.add(Account)");
        log.info(acc.toString());
        dao.add(acc);
    }

    @Override
    @Transactional
    public void delete(Account acc) {
        dao.delete(acc);
    }
    
    @Override
    @Transactional
    public void deleteByLogin(String login) {
        Account acc = getByLogin(login);
        delete(acc);
    }

    @Override
    @Transactional
    public void edit(Account acc) {
        log.info("service.edit(Account)");
        dao.edit(acc);
    }

    @Override
    @Transactional
    public Account getById(int id) {
        return dao.getById(id);
    }

    @Override
    @Transactional
    public Account getByLogin(String login) {
        return dao.getByLogin(login);
    }

    @Override
    @Transactional
    public boolean isLoginUnique(String login) {
        log.info("service.isLoginUnique for login {}", login);
        try {
            // TODO is it normal way, or realize by criteria without exceptions
            getByLogin(login);
        } catch (NoResultException ex) {
            log.info("unique by NoResultException");
            return true;
        }

        return false;
    }

    @Override
    @Transactional
    public AccountDTO entityToDTO(Account entity) {
        // ModelMapper modelMapper = new ModelMapper();
        return (new ModelMapper()).map(entity, AccountDTO.class);
    }

    @Override
    public Account dtoToEntity(AccountDTO dto) {
        return (new ModelMapper()).map(dto, Account.class);
    }

    @Override
    public List<AccountDTO> entityToDTO(List<Account> entities) {
        Type listType = new TypeToken<List<AccountDTO>>() {
        }.getType();
        List<AccountDTO> postDtoList = (new ModelMapper()).map(entities, listType);
        return postDtoList;
    }

    @Override
    public List<Account> dtoToEntity(List<AccountDTO> dtos) {
        return (new ModelMapper()).map(dtos, new TypeToken<List<Account>>() {
        }.getType());
    }

    // TODO LAZY query
//    @Override
//    @Transactional
//    public Account getById(int id) {
//        Account rez = dao.getById(id);
//        rez.setExtend_id(rez.getExtend_id());
//        return rez;
//        //return dao.getById(id);
//    }

}
