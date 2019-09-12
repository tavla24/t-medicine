package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum RoleType {
    ADMIN("ADMIN"), DOCTOR("DOCTOR"), NURSE("NURSE");

    String userProfileType;

    RoleType(String userProfileType) {
        this.userProfileType = userProfileType;
    }

    public String getUserProfileType() {
        return userProfileType;
    }

    public static List<String> getRoleTypesList() {
        List<String> list = new ArrayList<>();
        for (RoleType role: RoleType.values()) {
            list.add(role.name());
        }
        return list;
    }
}
