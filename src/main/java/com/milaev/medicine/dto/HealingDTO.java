package com.milaev.medicine.dto;

import com.milaev.medicine.model.Recipe;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

public class HealingDTO {
    private String name;
    private String type; // = ""; //HealingType.DRUG.getHealingType();

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
}
