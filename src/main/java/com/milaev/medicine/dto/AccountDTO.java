package com.milaev.medicine.dto;

import javax.validation.constraints.NotBlank;

import com.milaev.medicine.model.Person;
import com.milaev.medicine.model.Role;

import java.util.List;

public class AccountDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    // TODO @NotEmpty
    private RoleDTO role;

    //private Person person;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleDTO getRole() {
        if (this.role == null)
            this.role = new RoleDTO();
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return String.format("accounts result: login[%s]; password[%s]; role[%s]", login, password, getRole().getType());
    }
}
