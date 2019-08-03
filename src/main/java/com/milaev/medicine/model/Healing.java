package com.milaev.medicine.model;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import com.milaev.medicine.model.enums.HealingType;

@Entity
@Table(name = "healings")
public class Healing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String type; // = ""; //HealingType.DRUG.getHealingType();

    @OneToOne(mappedBy = "healing", fetch = FetchType.LAZY)
    private Recipe recipe;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
