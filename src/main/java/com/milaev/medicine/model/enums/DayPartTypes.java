package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum DayPartTypes {
    MORNING("Morning"), DAY("Day"), EVENING("Evening"), NIGHT("Night");

    String type;

    DayPartTypes(String dayPart) {
        this.type = dayPart;
    }

    public String getDayPart() {
        return type;
    }

    public static List<String> getTypeList() {
        List<String> list = new ArrayList<>();
        for (DayPartTypes type : DayPartTypes.values()) {
            list.add(type.name());
        }
        return list;
    }
}
