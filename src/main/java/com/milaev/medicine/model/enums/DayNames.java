package com.milaev.medicine.model.enums;

public enum DayNames {
    MONDAY("Monday"),
    TUESDAY("Tuesday"),
    WEDNESDAY("Wednesday"),
    THURSDAY("Thursday"),
    FRIDAY("Friday"),
    SATURDAY("Saturday"),
    SUNDAY("Sunday");

    String dayName;

    private DayNames(String dayName) {
        this.dayName = dayName;
    }

    public String getDayName() {
        return dayName;
    }
}
