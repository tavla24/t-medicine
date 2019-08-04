package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.dto.AccountDTO;

public interface AccountServiceInterface {
    List<AccountDTO> getAll();

    AccountDTO getByLogin(String login);

    AccountDTO getById(int id);

    boolean isLoginUnique(String login);

    boolean deleteByLogin(String login);

    boolean update(AccountDTO dto, String oldLogin);

    boolean insert(AccountDTO dto);
}
