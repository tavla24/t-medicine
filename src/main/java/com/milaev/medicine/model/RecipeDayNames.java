package com.milaev.medicine.model;

import java.util.Collection;
import javax.persistence.*;

@Entity
@Table(name = "recipes_day_names")
public class RecipeDayNames {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "name")
    private String dayName;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @OneToMany(mappedBy = "dayNames", fetch = FetchType.LAZY)
    private Collection<RecipeDayPart> dayParts;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Collection<RecipeDayPart> getDayParts() {
        return dayParts;
    }

    public void setDayParts(Collection<RecipeDayPart> dayParts) {
        this.dayParts = dayParts;
    }
}
