package com.milaev.medicine.dto;

import java.util.Date;

public class EventDTO {
    private Long id;
    private RecipeSimpleDTO recipe;
    private Date datestamp;
    private String status;
    private String info;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public RecipeSimpleDTO getRecipe() {
        if (recipe == null)
            recipe = new RecipeSimpleDTO();
        return recipe;
    }
    public void setRecipe(RecipeSimpleDTO recipe) {
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
