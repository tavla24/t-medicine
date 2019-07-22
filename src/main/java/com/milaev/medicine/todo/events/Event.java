package com.milaev.medicine.todo.events;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milaev.medicine.todo.patients.Patient;
import com.milaev.medicine.todo.recipes.Recipe;

@Component
public class Event {
    @Autowired
    //private Patient patient;
    private Recipe recipe;
    private Date date;
    private EventStatus status;
}
