package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum HealingType {
    PROCEDURE("Procedure"), DRUG("Drug");

    String type;

    HealingType(String type) {
        this.type = type;
    }

    public String getHealingType() {
        return type;
    }

    public static List<String> getTypeList() {
        List<String> list = new ArrayList<>();
        for (HealingType type : HealingType.values()) {
            list.add(type.name());
        }
        return list;
    }
}
