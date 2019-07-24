package com.milaev.medicine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.HealingType;

@Entity
@Table(name = "healings")
public class Healing {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(nullable = false)
    private long id;
    @Column
    private String name;
    @Column
    private HealingType type;

    public Healing() {
    }

    public Healing(long id, String name, HealingType type) {
        super();
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HealingType getType() {
        return type;
    }

    public void setType(HealingType type) {
        this.type = type;
    }

}
