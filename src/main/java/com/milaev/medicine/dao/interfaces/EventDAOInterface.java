package com.milaev.medicine.dao.interfaces;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.milaev.medicine.model.Event;

public interface EventDAOInterface {

    List<Event> getAll();

    List<Event> getByFilter(String queryString, Map<String, Object> queryParams);
    
    List<Event> getByRecipeId(Long id);

    List<Event> getByInsuranceId(String insuranceId);

    List<Event> getRecipesByTime();

    List<Event> getRecipesByTime(Date dateFrom);

    List<Event> getRecipesByTime(Date dateFrom, Date dateTo);

    void insert(Event acc);

    void delete(Event acc);

    void update(Event acc);
}
