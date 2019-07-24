package com.milaev.medicine.service.interfaces;

import java.util.List;

public interface TServiceInterface <T>{
    List<T> allAccounts();

    void add(T acc);

    void delete(T acc);

    void edit(T acc);

    T getById(int id);
}
