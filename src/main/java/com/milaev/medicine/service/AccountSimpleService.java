package com.milaev.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.dao.AccountSimpleDAO;
import com.milaev.medicine.model.AccountSimple;
import com.milaev.medicine.service.interfaces.TServiceInterface;

@Service
//@EnableTransactionManagement(proxyTargetClass = true)
public class AccountSimpleService implements TServiceInterface<AccountSimple> {

    @Autowired
    private AccountSimpleDAO dao;

    @Override
    @Transactional
    public List<AccountSimple> allAccounts() {
        return dao.allAccounts();
    }

    @Override
    @Transactional
    public void add(AccountSimple acc) {
        dao.add(acc);
    }

    @Override
    @Transactional
    public void delete(AccountSimple acc) {
        dao.delete(acc);
    }

    @Override
    @Transactional
    public void edit(AccountSimple acc) {
        dao.edit(acc);
    }

    @Override
    @Transactional
    public AccountSimple getById(int id) {
        return dao.getById(id);
    }

}
