package com.milaev.medicine.todo.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milaev.medicine.todo.healings.Healing;
import com.milaev.medicine.todo.patients.Patient;

@Component
public class Recipe {
    @Autowired
    private Patient patient;
    private Healing healing;

    private PeriodicHealing periodic;
    private PeriodicDuration duration;

    private int doze;

}
