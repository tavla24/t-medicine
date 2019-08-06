package com.milaev.medicine.dto;

import java.util.List;

public class DayNameDTO {
    private Long id;
    private String name;
    //private RecipeDTO recipe;
    private List<DayPartDTO> dayParts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

//    public RecipeDTO getRecipe() {
//        if (recipe == null)
//            recipe = new RecipeDTO();
//        return recipe;
//    }
//    public void setRecipe(RecipeDTO recipe) {
//        this.recipe = recipe;
//    }


    public List<DayPartDTO> getDayParts() {
        return dayParts;
    }

    public void setDayParts(List<DayPartDTO> dayParts) {
        this.dayParts = dayParts;
    }
}
