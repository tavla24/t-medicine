package com.milaev.medicine.service.interfaces;

import java.util.List;

public interface TServiceInterface <E, D>{
    List<E> allAccounts();

    void add(E acc);

    void delete(E acc);

    void edit(E acc);

    E getById(int id);

    E getByLogin(String login);
    
    D entityToDTO(E entity);
    
    E dtoToEntity(D dto);
}
