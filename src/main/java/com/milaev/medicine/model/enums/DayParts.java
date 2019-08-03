package com.milaev.medicine.model.enums;

public enum DayParts {
    MORNING("Morning"), DAY("Day"), EVENING("Evening"), NIGHT("Night");

    String dayPart;

    private DayParts(String dayPart) {
        this.dayPart = dayPart;
    }

    public String getDayPart() {
        return dayPart;
    }
}
