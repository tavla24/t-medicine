package com.milaev.medicine.dto;

import java.util.Date;

public class EventDTO {
    private RecipeDTO recipe;
    private Date datestamp;
    private String status;
    private String info;
    
    public RecipeDTO getRecipe() {
        if (recipe == null)
            recipe = new RecipeDTO();
        return recipe;
    }
    public void setRecipe(RecipeDTO recipe) {
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