package com.milaev.medicine.mvc.model.events;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milaev.medicine.mvc.model.patients.Patient;
import com.milaev.medicine.mvc.model.recipes.Recipe;

@Component
public class Event {
    @Autowired
    //private Patient patient;
    private Recipe recipe;
    private Date date;
    private EventStatus status;
}
