package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Event;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface EventDAOInterface {

    List<Event> getAll();

    Long getCountByFilter(String queryString, Map<String, Object> queryParams);

    List<Event> getByFilter(String queryString, Map<String, Object> queryParams);

    List<Event> getByFilter(String queryString, Map<String, Object> queryParams, int start, int size);
    
    List<Event> getByRecipeId(Long id);

    List<Event> getByInsuranceId(String insuranceId);

    List<Event> getEventsByTime();

    List<Event> getEventsByTime(Date dateFrom);

    List<Event> getEventsByTime(Date dateFrom, Date dateTo);

    void insert(Event acc);

    void delete(Event acc);

    void update(Event acc);
}
