package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.utils.MapperUtil;

public class AccountConverter {

    public static AccountDTO toDTO(Account db){
        AccountDTO dto = new AccountDTO();
        MapperUtil.toDTOAccount().accept(db, dto);

        dto.setOldLogin(db.getLogin());
        dto.setOldPassword(db.getPassword());
        return dto;
    }

    public static Account toEntity(AccountDTO dto, Account db){
        MapperUtil.toEntityAccount().accept(dto, db);
        return db;
    }
}
