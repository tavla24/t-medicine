package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum PatientStatus {
    ILL("Ill"), HEALTHY("Healthy");

    String status;

    PatientStatus(String status) {
        this.status = status;
    }

    public String getPatientStatus() {
        return status;
    }

    public static List<String> getPatientStatusList() {
        List<String> list = new ArrayList<>();
        for (PatientStatus status : PatientStatus.values()) {
            list.add(status.name());
        }
        return list;
    }
}
