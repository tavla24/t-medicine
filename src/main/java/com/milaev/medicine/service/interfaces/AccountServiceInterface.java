package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.mvc.model.accounts.Account;

public interface AccountServiceInterface {
    List<Account> allAccounts();

    void add(Account acc);

    void delete(Account acc);

    void edit(Account acc);

    Account getById(int id);
}
