package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Account;

import java.util.List;

public interface AccountDAOInterface {

    List<Account> getAll();

    Account getByLogin(String login);

    void insert(Account acc);

    void delete(Account acc);

    void update(Account acc);
}
