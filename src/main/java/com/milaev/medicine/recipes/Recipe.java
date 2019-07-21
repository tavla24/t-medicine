package com.milaev.medicine.recipes;

import com.milaev.medicine.patients.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Recipe {
    @Autowired
    private Patient patient;

    private PeriodicHealing periodic;
    private PeriodicDuration duration;

    private int doze;

}
