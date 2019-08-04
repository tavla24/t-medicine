package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Account;

public interface AccountDAOInterface {
    List<Account> getAll();

    Account getById(int id);

    Account getByLogin(String login);

    boolean insert(Account acc);

    boolean delete(Account acc);

    boolean update(Account acc);
}
