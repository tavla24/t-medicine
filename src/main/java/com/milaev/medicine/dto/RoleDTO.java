package com.milaev.medicine.dto;

import java.util.Collection;

import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.enums.RoleType;

public class RoleDTO {
    private String type = RoleType.USER.getUserProfileType();
    private Collection<Account> accounts;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }
}
