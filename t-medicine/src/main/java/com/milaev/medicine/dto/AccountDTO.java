package com.milaev.medicine.dto;

public class AccountDTO {
    private String login;
    private String password;

    private String oldLogin;
    private String oldPassword;

    private RoleDTO role;

    private boolean edit;

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

    public String getOldLogin() {
        return oldLogin;
    }

    public void setOldLogin(String oldLogin) {
        this.oldLogin = oldLogin;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public RoleDTO getRole() {
        if (this.role == null)
            this.role = new RoleDTO();
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    @Override
    public String toString() {
        return String.format("accounts result: login[%s]; password[%s]; role[%s]", login, password, getRole().getType());
    }
}
