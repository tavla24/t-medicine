package com.milaev.medicine.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)//, cascade = { CascadeType.REMOVE, CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "recipe_id", nullable = false)
    private RecipeSimple recipe;
    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datestamp;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "info", nullable = false)
    private String info;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RecipeSimple getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeSimple recipe) {
        this.recipe = recipe;
    }

    public Date getDatestamp() {
        return datestamp;
    }

    public void setDatestamp(Date datestamp) {
        this.datestamp = datestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
