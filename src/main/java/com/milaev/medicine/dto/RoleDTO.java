package com.milaev.medicine.dto;

import java.util.Collection;
import java.util.List;

import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.enums.RoleType;

public class RoleDTO {
    private String type;// = RoleType.USER.getUserProfileType();

    private List<Account> accounts;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }
}
