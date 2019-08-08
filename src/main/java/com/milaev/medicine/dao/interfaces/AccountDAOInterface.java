package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Account;

public interface AccountDAOInterface {

    List<Account> getAll();

    Account getByLogin(String login);

    void insert(Account acc);

    void delete(Account acc);

    void update(Account acc);
}
