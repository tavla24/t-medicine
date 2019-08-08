package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.DayName;

public interface DayNameDAOInterface {
    
    List<DayName> getByRecipeId(Long id);

    void insert(DayName acc);

    void delete(DayName acc);

    void update(DayName acc);
}
