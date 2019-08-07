package com.milaev.medicine.model.enums;

import java.util.ArrayList;
import java.util.List;

public enum EventStatus {
    PLAN("Plan"), DONE("Done"), CANCEL("Cancel");

    String status;

    private EventStatus(String status) {
        this.status = status;
    }

    public String getEventStatus() {
        return status;
    }

    public static List<String> getStatusList() {
        List<String> list = new ArrayList<>();
        for (EventStatus status : EventStatus.values()) {
            list.add(status.name());
        }
        return list;
    }
}
