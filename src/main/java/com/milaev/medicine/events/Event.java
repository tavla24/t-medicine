package com.milaev.medicine.events;

import com.milaev.medicine.healings.Healing;
import com.milaev.medicine.patients.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class Event {
    @Autowired
    private Patient patient;
    private Date date;
    private Healing healing;
    private EventStatus status;
}
