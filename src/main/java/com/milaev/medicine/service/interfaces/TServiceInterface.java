package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.model.Account;

public interface TServiceInterface <T>{
    List<T> allAccounts();

    void add(T acc);

    void delete(T acc);

    void edit(T acc);

    T getById(int id);

    List<T> getByLogin(String login);
    T getByLoginSingle(String login);
}
