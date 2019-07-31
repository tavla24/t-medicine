package com.milaev.medicine.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Service
public class AccountService implements TServiceInterface<Account, AccountDTO> {

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
        dao.add(acc);
    }

    @Override
    @Transactional
    public void delete(Account acc) {
        dao.delete(acc);
    }

    @Override
    @Transactional
    public void edit(Account acc) {
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
    public AccountDTO entityToDTO(Account entity) {
        // ModelMapper modelMapper = new ModelMapper();
        return (new ModelMapper()).map(entity, AccountDTO.class);
    }

    @Override
    public Account dtoToEntity(AccountDTO dto) {
        return (new ModelMapper()).map(dto, Account.class);
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
