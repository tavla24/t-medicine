package com.milaev.medicine.dto;

public class PersonDTO {
    private String name;
    private String surname;
    private String patronymic;
    private String email;
    private String phone;
    private AccountDTO account;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AccountDTO getAccount() {
        if (account == null)
            account = new AccountDTO();
        return account;
    }

    public void setAccount(AccountDTO account) {
        if (account == null)
            account = new AccountDTO();
        this.account = account;
    }

    public String getLogin() {
        if (this.getAccount() == null)
            this.setAccount(new AccountDTO());
        return getAccount().getLogin();
    }

    public void setLogin(String login) {
        if (this.getAccount() == null)
            this.setAccount(new AccountDTO());
        getAccount().setLogin(login);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(account.toString());
        sb.append(String.format("PersonDTO result: name[%s]; surname[%s]; patronymic[%s], email[%s], phone[%s]", name,
                surname, patronymic, email, phone));
        return sb.toString();
    }
}
