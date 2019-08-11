package com.milaev.medicine.dto;

import com.milaev.medicine.model.enums.DayPartTypes;

import java.time.DayOfWeek;
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
    private List<String> dayPartsList;

    private List<DayOfWeek> dayOfWeekList;
    private List<DayPartTypes> partOfDayList;
    
    private List<String> dayNamesListLocale;

    public static final String SPLITTER = ";";

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
        return dayNames;
    }

    public void setDayNames(String dayNames) {
        this.dayNames = dayNames;
        convToDayNamesList();
    }

    public String getDayParts() {
        return dayParts;
    }

    public void setDayParts(String dayParts) {
        this.dayParts = dayParts;
        convToDayPartsList();
    }

    public List<String> getDayNamesList() {
        return dayNamesList;
    }

    public void setDayNamesList(List<String> dayNamesList) {
        this.dayNamesList = dayNamesList;
        convListToDayNames();
    }

    public List<String> getDayPartsList() {
        return dayPartsList;
    }

    public void setDayPartsList(List<String> dayPartsList) {
        this.dayPartsList = dayPartsList;
        convListToDayParts();
    }

    private void convToDayNamesList() {
        dayNamesList = new ArrayList<>();
        if (dayNames != null)
            dayNamesList = Arrays.asList(dayNames.split(SPLITTER));
    }

    private void convToDayPartsList() {
        dayPartsList = new ArrayList<>();
        if (dayParts != null)
            dayPartsList = Arrays.asList(dayParts.split(SPLITTER));
    }

    private void convListToDayNames() {
        StringBuilder sb = new StringBuilder();
        for (String item : this.dayNamesList)
            sb.append(String.format("%s%s", item, SPLITTER));
        this.dayNames = sb.toString();
    }

    private void convListToDayParts() {
        StringBuilder sb = new StringBuilder();
        for (String item : this.dayPartsList)
            sb.append(String.format("%s%s", item, SPLITTER));
        this.dayParts = sb.toString();
    }

    public List<String> getDayNamesListLocale() {
        return dayNamesListLocale;
    }

    public void setDayNamesListLocale(List<String> dayNamesListLocale) {
        this.dayNamesListLocale = dayNamesListLocale;
    }

    public void translate() {
        dayNamesListLocale = new ArrayList<>();
        for (String str : dayNamesList)
            dayNamesListLocale.add(str.toLowerCase());
    }

    public List<DayOfWeek> getDayOfWeekList() {
        convToDayNamesList();
        dayOfWeekList = new ArrayList<>();
        for (DayOfWeek itemDOW : DayOfWeek.values())
            for (String itemS : getDayNamesList())
                if (itemDOW.name().toLowerCase().equals(itemS.toLowerCase()))
                    dayOfWeekList.add(itemDOW);

        return dayOfWeekList;
    }

    public List<DayPartTypes> getPartOfDayList() {
        convToDayPartsList();
        partOfDayList = new ArrayList<>();
        for (DayPartTypes itemDPT : DayPartTypes.values())
            for (String itemS : getDayPartsList())
                if (itemDPT.name().toLowerCase().equals(itemS.toLowerCase()))
                    partOfDayList.add(itemDPT);

        return partOfDayList;
    }
}
