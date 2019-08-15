package com.milaev.medicine.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class AccountDTO {
    private String login;
    private String password;
    private RoleDTO role;

    //private PersonDTO person;

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
