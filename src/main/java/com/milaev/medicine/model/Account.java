package com.milaev.medicine.model;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
//@Proxy(lazy =false) 
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    @Column(nullable = false)
    private String password;

    // TODO point 1
    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToOne(mappedBy = "account", fetch = FetchType.LAZY, cascade = { CascadeType.REMOVE})
    private Person person;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
