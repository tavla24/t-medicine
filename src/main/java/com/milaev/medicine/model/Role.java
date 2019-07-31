package com.milaev.medicine.model;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.RoleType;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false)
    private long id;

    @Column(name = "type", nullable = false)
    private RoleType type;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Collection<Account> accounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType roleType) {
        this.type = roleType;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccount(Collection<Account> accounts) {
        this.accounts = accounts;
    }
}
