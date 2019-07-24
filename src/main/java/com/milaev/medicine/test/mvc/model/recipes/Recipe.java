package com.milaev.medicine.test.mvc.model.recipes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.milaev.medicine.test.mvc.model.healings.Healing;
import com.milaev.medicine.test.mvc.model.patients.Patient;

@Component
public class Recipe {
    @Autowired
    private Patient patient;
    private Healing healing;

    private PeriodicHealing periodic;
    private PeriodicDuration duration;

    private int doze;

}
