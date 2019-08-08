package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.DayPart;

public interface DayPartDAOInterface {
    
    List<DayPart> getByDayNameId(Long id);

    void insert(DayPart acc);

    void delete(DayPart acc);

    void update(DayPart acc);
}
