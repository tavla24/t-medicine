package com.milaev.medicine.model.enums;

public enum RoleType {
    ROOT("ROOT"), ADMIN("ADMIN"), USER("USER"), DOCTOR("DOCTOR"), NURSE("NURSE"), PATIENT("PATIENT");

    String userProfileType;

    private RoleType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }
}
