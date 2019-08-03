package com.milaev.medicine.model.enums;

public enum HealingType {
    PROCEDURE("Procedure"), DRUG("Drug");

    String type;

    private HealingType(String type) {
        this.type = type;
    }

    public String getHealingType() {
        return type;
    }
}
