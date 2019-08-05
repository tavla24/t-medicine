package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.DayName;

public interface DayNameDAOInterface {
    
    List<DayName> getByRecipeId(Long id);

    boolean insert(DayName acc);

    boolean delete(DayName acc);

    boolean update(DayName acc);
}
