package com.milaev.medicine.utils.datetime;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaysOfWeekContainer {

    private List<DayOfWeekContainer> list = new ArrayList<>();

    public void fill(Date from, Date to, DayOfWeek... daysOfWeek) {
        for (DayOfWeek item : daysOfWeek)
            list.add((new DayOfWeekContainer(item)).fill(from, to));
    }

    public void fill(Date from, Date to, List<DayOfWeek> daysOfWeek) {
        for (DayOfWeek item : daysOfWeek)
            list.add((new DayOfWeekContainer(item)).fill(from, to));
    }

    public List<DayOfWeekContainer> getList() {
        return list;
    }
    
    public List<Date> getList(DayOfWeek dayOfWeek) {
        for(DayOfWeekContainer item: list)
            if(dayOfWeek == item.getDayOfWeek())
                return item.getList();
        return new ArrayList<Date>();
    }
}
