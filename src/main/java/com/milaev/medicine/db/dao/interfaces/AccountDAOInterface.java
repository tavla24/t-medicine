package com.milaev.medicine.db.dao.interfaces;

import java.util.List;

import com.milaev.medicine.db.entity.Account;

public interface AccountDAOInterface {
    List<Account> allAccounts();

    void add(Account acc);

    void delete(Account acc);

    void edit(Account acc);

    Account getById(int id);
}
