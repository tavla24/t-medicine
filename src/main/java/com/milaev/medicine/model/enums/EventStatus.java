package com.milaev.medicine.model.enums;

public enum EventStatus {
    PLAN("Plan"), DONE("Done"), CANCEL("Cancel");

    String status;

    private EventStatus(String status) {
        this.status = status;
    }

    public String getEventStatus() {
        return status;
    }
}
