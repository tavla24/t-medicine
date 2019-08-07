package com.milaev.medicine.dao.interfaces;

import java.util.Date;
import java.util.List;

import com.milaev.medicine.model.Event;

public interface EventDAOInterface {
    
    List<Event> getByRecipeId(Long id);

    List<Event> getRecipesByTime();

    List<Event> getRecipesByTime(Date dateFrom);

    List<Event> getRecipesByTime(Date dateFrom, Date dateTo);

    boolean insert(Event acc);

    boolean delete(Event acc);

    boolean update(Event acc);
}
