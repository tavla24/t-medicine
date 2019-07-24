package com.milaev.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.AccessLevelType;

@Entity
@Table(name = "accounts_ext")
//@Proxy(lazy =false) 
public class AccountExt {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private long password_hash;
    @Column(nullable = false)
    private AccessLevelType access_level;

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

    public long getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(long password_hash) {
        this.password_hash = password_hash;
    }

    public AccessLevelType getAccess_level() {
        return access_level;
    }

    public void setAccess_level(AccessLevelType access_level) {
        this.access_level = access_level;
    }

}
