package com.milaev.medicine.dto.assistants;

import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.utils.datetime.DateUtils;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventFilterDTOAssistant {

    private String queryString;
    private Map<String, Object> queryParams;

    public void createQuery(EventFilterDTO eventFilterDTO, boolean pageable){
        StringBuilder sb = new StringBuilder();

        if (pageable)
            sb.append("select count(*) ");

        sb.append("from Event as f");

        List<String> queryStringBuilder = new ArrayList<>();
        queryParams = new HashMap<>();

        if (isValid(eventFilterDTO.getName())) {
            queryStringBuilder.add(" f.recipe.patient.name LIKE :p_name");
            queryParams.put("p_name", "%" + eventFilterDTO.getName() + "%");
        }
        if (isValid(eventFilterDTO.getSurname())) {
            queryStringBuilder.add(" f.recipe.patient.surname LIKE :p_surname");
            queryParams.put("p_surname", "%" + eventFilterDTO.getSurname() + "%");
        }
        if (eventFilterDTO.getNextHours() != null) {
            Date currDateTime = new Date();
            Date nextDateTime = DateUtils.setTimeInc(currDateTime, eventFilterDTO.getNextHours(), 0);
            queryStringBuilder.add(" f.datestamp > :p_date_now and f.datestamp < :p_date_next");
            queryParams.put("p_date_now", currDateTime);
            queryParams.put("p_date_next", nextDateTime);
        } else {
            if (eventFilterDTO.getDateFrom() != null) {
                queryStringBuilder.add(" f.datestamp > :p_date_from");
                queryParams.put("p_date_from", eventFilterDTO.getDateFrom());
            }
            if (eventFilterDTO.getDateTo() != null) {
                queryStringBuilder.add(" f.datestamp < :p_date_to");
                queryParams.put("p_date_to", eventFilterDTO.getDateTo());
            }
        }
        if (isValid(eventFilterDTO.getStatus())) {
            queryStringBuilder.add(" f.status = :p_status");
            queryParams.put("p_status", eventFilterDTO.getStatus());
        }
        if (isValid(eventFilterDTO.getHealingType())) {
            queryStringBuilder.add(" f.recipe.healingType = :p_healing_type");
            queryParams.put("p_healing_type", eventFilterDTO.getHealingType());
        }

        for (int i = 0; i < queryStringBuilder.size(); i++) {
            if (i == 0)
                sb.append(" where");
            sb.append(queryStringBuilder.get(i));
            if (i < queryStringBuilder.size() - 1)
                sb.append(" and");
        }

        if (eventFilterDTO.isSortByTime())
            sb.append(" order by f.datestamp");

        queryString = sb.toString();
    }

    public String getQueryString() {
        return queryString;
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    private boolean isValid(String str){
        return (str != null) && (!str.isEmpty());
    }
}
