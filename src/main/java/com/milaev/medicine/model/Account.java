package com.milaev.medicine.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
    @ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "extend_id", nullable = false)
    private AccountExt extend;

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

    public AccountExt getExtend_id() {
        return extend;
    }

    public void setExtend_id(AccountExt extend_id) {
        this.extend = extend_id;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("id: %d; name: %s\n", id, name));
        if (extend != null)
            sb.append(String.format("\t id: %d; login: %s; password_hash: %d; access_level: %s\n ", extend.getId(),
                    extend.getLogin(), extend.getPassword_hash(), extend.getAccess_level().toString()));
        else
            sb.append("\t no extend_id");
        return sb.toString();
    }
}
