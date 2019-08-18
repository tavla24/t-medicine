package com.milaev.medicine.dto;

import com.milaev.medicine.model.enums.HealingType;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.utils.datetime.DateUtils;

import java.util.*;

public class EventFilterDTO {
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private Date dateFrom;
    private Date dateTo;
    private Integer nextHours;
    private String status;
    private String healingType;
    private boolean sortByTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
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

    public Integer getNextHours() {
        return nextHours;
    }

    public void setNextHours(Integer nextHours) {
        this.nextHours = nextHours;
    }

    public String getHealingType() {
        return healingType;
    }

    public void setHealingType(String healingType) {
        this.healingType = healingType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSortByTime() {
        return sortByTime;
    }

    public void setSortByTime(boolean sortByTime) {
        this.sortByTime = sortByTime;
    }

    public List<String> getStatuses() {
        return PatientStatus.getPatientStatusList();
    }

    public List<String> getHealingTypes() {
        return HealingType.getTypeList();
    }
}
