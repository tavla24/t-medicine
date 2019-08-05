package com.milaev.medicine.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.RoleType;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;// = RoleType.USER.getUserProfileType();

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String roleType) {
        this.type = roleType;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccount(List<Account> accounts) {
        this.accounts = accounts;
    }

    public static List<String> getRoleTypesList(){
        return RoleType.getRoleTypesList();
    }
}
