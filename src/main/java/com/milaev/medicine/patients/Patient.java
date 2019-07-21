package com.milaev.medicine.patients;

import org.springframework.stereotype.Component;

@Component
public class Patient {
    private String name;
    private int insuranceId;

    private String doctor;
    private String diagnosis;

    private PatientStatus status;
}
