package com.milaev.medicine.dto;

import com.milaev.medicine.model.enums.EventStatus;
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

    private String queryString;
    private Map<String, Object> queryParams;

    // TODO filt by date/name/...

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

    public List<String> getStatuses() {
        return EventStatus.getStatusList();
    }

    public List<String> getHealingTypes() {
        return HealingType.getTypeList();
    }

    public void createQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("from Event as f");

        List<String> queryStringBuilder = new ArrayList<>();
        queryParams = new HashMap<>();

        if (isValid(name)) {
            queryStringBuilder.add(" f.recipe.patient.name = :p_name");
            queryParams.put("p_name", name);
        }
        if (isValid(surname)) {
            queryStringBuilder.add(" f.recipe.patient.surname = :p_surname");
            queryParams.put("p_surname", surname);
        }
        if (nextHours != null) {
            Date currDateTime = new Date();
            Date nextDateTime = DateUtils.setTimeInc(currDateTime, nextHours, 0);
            queryStringBuilder.add(" f.datestamp > :p_date_now and f.datestamp < :p_date_next");
            queryParams.put("p_date_now", currDateTime);
            queryParams.put("p_date_next", nextDateTime);
        } else {
            if (dateFrom != null) {
                queryStringBuilder.add(" f.datestamp > :p_date_from");
                queryParams.put("p_date_from", dateFrom);
            }
            if (dateTo != null) {
                queryStringBuilder.add(" f.datestamp < :p_date_to");
                queryParams.put("p_date_to", dateTo);
            }
        }
        if (isValid(status)) {
            queryStringBuilder.add(" f.status = :p_status");
            queryParams.put("p_status", status);
        }
        if (isValid(healingType)) {
            queryStringBuilder.add(" f.recipe.healingType = :p_healing_type");
            queryParams.put("p_healing_type", healingType);
        }

        for (int i = 0; i < queryStringBuilder.size(); i++) {
            if (i == 0)
                sb.append(" where");
            sb.append(queryStringBuilder.get(i));
            if (i < queryStringBuilder.size() - 1)
                sb.append(" and");
        }
        sb.append(" order by f.datestamp");

        queryString = sb.toString();
    }

    private boolean isValid(String str){
        return (str != null) && (!str.isEmpty());
    }

    public String getQueryString() {
        return queryString;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }
}
