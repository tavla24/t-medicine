package com.milaev.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "accounts")
//@Proxy(lazy =false) 
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    @ManyToOne // (cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY)
    private Person person;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public long getRoleID() {
        if (this.role == null)
            this.role = new Role();
        return role.getId();
    }

    public void setRole(String role) {
        if (this.role == null)
            this.role = new Role();
        this.role.setType(role);
    }

    public void setRoleID(long id) {
        if (this.role == null)
            this.role = new Role();
        this.role.setId(id);
    }

    public void setRole(Role r) {
        this.role = r;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return String.format("accounts result: id[%d]; login[%s]; password[%s]; access_level[%s]", id, login, password,
                role.getType());
    }
}
