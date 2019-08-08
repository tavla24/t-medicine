package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.dto.AccountDTO;

public interface AccountServiceInterface {
    List<AccountDTO> getAll();

    AccountDTO getByLogin(String login);

    AccountDTO getById(Long id);

    boolean isLoginUnique(String login);

    void deleteByLogin(String login);

    void update(AccountDTO dto, String oldLogin);

    void insert(AccountDTO dto);
}
