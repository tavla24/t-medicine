package com.milaev.medicine.model.enums;

public enum PatientStatus {
    ILL("Ill"), HEALTHY("Healthy");

    String status;

    private PatientStatus(String status) {
        this.status = status;
    }

    public String getPatientStatus() {
        return status;
    }
}
