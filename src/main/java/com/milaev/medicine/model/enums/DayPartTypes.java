package com.milaev.medicine.model.enums;

public enum DayPartTypes {
    MORNING("Morning"), DAY("Day"), EVENING("Evening"), NIGHT("Night");

    String type;

    private DayPartTypes(String dayPart) {
        this.type = dayPart;
    }

    public String getDayPart() {
        return type;
    }
}
