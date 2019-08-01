package com.milaev.medicine.dto;

import javax.validation.constraints.NotBlank;

import com.milaev.medicine.model.Role;

public class AccountDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    // TODO @NotEmpty
    private Role role;

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

    public String getRole() {
        if (this.role == null)
            this.role = new Role();
        return role.getType();
    }

    public void setRole(String role) {
        if (this.role == null)
            this.role = new Role();
        this.role.setType(role);
    }

    @Override
    public String toString() {
        return String.format("accounts result: login[%s]; password[%s]; access_level[%s]", login, password,
                role.getType());
    }
}
