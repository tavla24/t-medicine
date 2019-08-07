package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum DayNameTypes {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    String type;

    private DayNameTypes(String dayName) {
        this.type = dayName;
    }

    public String getDayName() {
        return type;
    }

    public static List<String> getTypeList() {
        List<String> list = new ArrayList<>();
        for (DayNameTypes type : DayNameTypes.values()) {
            list.add(type.name());
        }
        return list;
    }
}
