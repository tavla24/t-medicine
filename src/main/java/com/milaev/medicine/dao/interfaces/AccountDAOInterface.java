package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Account;

public interface AccountDAOInterface {
    List<Account> allAccounts();

    void add(Account acc);

    void delete(Account acc);

    void edit(Account acc);

    Account getById(int id);
}
