package com.milaev.medicine.utils;

import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;

@Component
public class MapperAccounts {

    private static ModelMapper mapperToDTO;
    private static ModelMapper mapperToEntity;

    public static BiConsumer<AccountDTO, Account> toEntity() {
        return mapperToEntity::map;
    }

    public static BiConsumer<Account, AccountDTO> toDTO() {
        return mapperToDTO::map;
    }

    @PostConstruct
    public void postConstruct() {
        mapperToEntity = new ModelMapper();
        mapperToEntity.createTypeMap(AccountDTO.class, Account.class).setPropertyCondition(Conditions.isNotNull());
        // .addMappings(map -> map.skip(Account::setId));

        mapperToDTO = new ModelMapper();
        mapperToDTO.createTypeMap(Account.class, AccountDTO.class).setPropertyCondition(Conditions.isNotNull());
        // .addMappings(map -> map.skip(Account::setId));
    }
}
