package com.milaev.medicine.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RecipeSimpleDTO {
    private Long id;
    private PatientDTO patient;

    private String healingName;
    private String healingType;
    private boolean healthful;

    private Date dateFrom;
    private Date dateTo;

    private String doze;

    private String dayNames;
    private String dayParts;

    private List<String> dayNamesList;
    private List<String> dayNamesListLocale;
    private List<String> dayPartsList;

    private String splitter = ";";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDTO getPatient() {
        if (patient == null)
            patient = new PatientDTO();
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }

    public String getHealingName() {
        return healingName;
    }

    public void setHealingName(String healingName) {
        this.healingName = healingName;
    }

    public String getHealingType() {
        return healingType;
    }

    public void setHealingType(String healingType) {
        this.healingType = healingType;
    }

    public boolean isHealthful() {
        return healthful;
    }

    public void setHealthful(boolean healthful) {
        this.healthful = healthful;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getDoze() {
        return doze;
    }

    public void setDoze(String doze) {
        this.doze = doze;
    }

    public String getDayNames() {
        if (dayNames == null)
            dayNames = "";
        return dayNames;
    }

    public void setDayNames(String dayNames) {
        this.dayNames = dayNames;
    }

    public String getDayParts() {
        if (dayParts == null)
            dayParts = "";
        return dayParts;
    }

    public void setDayParts(String dayParts) {
        this.dayParts = dayParts;
    }

    public List<String> getDayNamesList() {
        if(dayNamesList == null)
            dayNamesList = new ArrayList<>();
        return dayNamesList;
    }

    public void setDayNamesList(List<String> dayNamesList) {
        this.dayNamesList = dayNamesList;
    }

    public List<String> getDayPartsList() {
        if(dayPartsList == null)
            dayPartsList = new ArrayList<>();
        return dayPartsList;
    }

    public void setDayPartsList(List<String> dayPartsList) {
        this.dayPartsList = dayPartsList;
    }

    public void convToDayNamesList(){
        this.dayNamesList = Arrays.asList(getDayNames().split(splitter));
    }

    public void convToDayPartsList(){
        this.dayPartsList = Arrays.asList(getDayParts().split(splitter));
    }

    public void convListToDayNames(){
        StringBuilder sb = new StringBuilder();
        for(String item: dayNamesList)
            sb.append(String.format("%s%s", item, splitter));
        dayNames = sb.toString();
    }

    public void convListToDayTypes(){
        StringBuilder sb = new StringBuilder();
        for(String item: dayPartsList)
            sb.append(String.format("%s%s", item, splitter));
        dayParts = sb.toString();
    }

    public List<String> getDayNamesListLocale() {
        return dayNamesListLocale;
    }

    public void setDayNamesListLocale(List<String> dayNamesListLocale) {
        this.dayNamesListLocale = dayNamesListLocale;
    }

    public void translate(){
        dayNamesListLocale = new ArrayList<>();
        for (String str: dayNamesList)
            dayNamesListLocale.add(str.toLowerCase());
    }
}
