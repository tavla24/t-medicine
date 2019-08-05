package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.DayPart;

public interface DayPartDAOInterface {
    
    List<DayPart> getByDayNameId(Long id);

    boolean insert(DayPart acc);

    boolean delete(DayPart acc);

    boolean update(DayPart acc);
}
