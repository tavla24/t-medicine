package com.milaev.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.AccessLevelType;

@Entity
@Table(name = "accounts")
//@Proxy(lazy =false) 
public class Account {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private String name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return String.format("accounts result: id[%d]; name[%s]; login[%s]; password_hash[%d]; access_level[%s]", id,
                name, login, password_hash, access_level.name());
    }
}
