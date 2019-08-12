package com.milaev.medicine.dto;

import javax.validation.constraints.NotBlank;

public class AccountDTO {
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    // TODO @NotEmpty
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

//    public PersonDTO getPerson() {
//        if (this.person == null)
//            this.person = new PersonDTO();
//        return person;
//    }
//
//    public void setPerson(PersonDTO person) {
//        this.person = person;
//    }

    @Override
    public String toString() {
        return String.format("accounts result: login[%s]; password[%s]; role[%s]", login, password, getRole().getType());
    }
}
