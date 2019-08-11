package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum DayPartTypes {
    MORNING(6), DAY(12), EVENING(18), NIGHT(24);

    int time;

    DayPartTypes(int time) {
        this.time = time;
    }

    public int getDayPartTime() {
        return time;
    }

    public static List<String> getTypeList() {
        List<String> list = new ArrayList<>();
        for (DayPartTypes item : DayPartTypes.values()) {
            list.add(item.name());
        }
        return list;
    }
}
