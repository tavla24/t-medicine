package com.milaev.medicine.model;

import com.milaev.medicine.model.enums.RoleType;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(name = "type", nullable = false, unique = true)
    private String type;// = RoleType.USER.getUserProfileType();

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private List<Account> accounts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
