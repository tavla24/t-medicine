package com.milaev.medicine.model.enums;

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
}
