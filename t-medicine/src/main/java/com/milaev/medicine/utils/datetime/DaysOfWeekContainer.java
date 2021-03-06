package com.milaev.medicine.utils.datetime;

import com.milaev.medicine.model.enums.DayPartTypes;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaysOfWeekContainer {

    private List<DayOfWeekContainer> list = new ArrayList<>();

//    public void fill(Date from, Date to, DayOfWeek... daysOfWeek) {
//        for (DayOfWeek item : daysOfWeek)
//            list.add((new DayOfWeekContainer(item)).fill(from, to));
//    }

    public void fill(Date from, Date to, List<DayOfWeek> daysOfWeek, List<DayPartTypes> dayPartsList) {
        for (DayOfWeek item : daysOfWeek)
            list.add((new DayOfWeekContainer(item)).fill(from, to, dayPartsList));
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

    public int getDateTimeCount(){
        int counter = 0;
        for(DayOfWeekContainer item: list)
            counter += item.getList().size();
        return counter;
    }
}
