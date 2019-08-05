package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Event;

public interface EventDAOInterface {
    
    List<Event> getByRecipeId(Long id);

    boolean insert(Event acc);

    boolean delete(Event acc);

    boolean update(Event acc);
}
