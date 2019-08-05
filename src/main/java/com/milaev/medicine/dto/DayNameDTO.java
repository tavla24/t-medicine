package com.milaev.medicine.dto;

public class DayNameDTO {
    private String name;
    private RecipeDTO recipe;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public RecipeDTO getRecipe() {
        if (recipe == null)
            recipe = new RecipeDTO();
        return recipe;
    }
    public void setRecipe(RecipeDTO recipe) {
        this.recipe = recipe;
    }
    
    
}
