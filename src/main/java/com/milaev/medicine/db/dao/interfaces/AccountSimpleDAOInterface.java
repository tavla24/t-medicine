package com.milaev.medicine.db.dao.interfaces;

import java.util.List;

import com.milaev.medicine.db.entity.AccountSimple;

public interface AccountSimpleDAOInterface {
    List<AccountSimple> allAccounts();

    void add(AccountSimple acc);

    void delete(AccountSimple acc);

    void edit(AccountSimple acc);

    AccountSimple getById(int id);
}
