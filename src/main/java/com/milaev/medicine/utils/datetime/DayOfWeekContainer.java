package com.milaev.medicine.utils.datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.milaev.medicine.model.enums.DayPartTypes;

public class DayOfWeekContainer {
    private DayOfWeek dayOfWeek;
    //private List<DayPartTypes> dayPartsList;
    private List<Date> list = new ArrayList<>();

    public DayOfWeekContainer(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeekContainer fill(Date from, Date to, List<DayPartTypes> dayPartsList) {
        LocalDateTime ldFrom = DateUtils.asLocalDateTime(from);
        LocalDateTime ldTo = DateUtils.asLocalDateTime(to);

        for (LocalDateTime date = ldFrom; date.isBefore(ldTo) || date.isEqual(ldTo); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == (dayOfWeek)) {
                if (dayPartsList != null) {
                    for (DayPartTypes item: dayPartsList) {
                        list.add(DateUtils.asDate(DateUtils.setTime(date, item.getDayPartTime(), 0)));
                    }
                } else
                list.add(DateUtils.asDate(date));
            }
        }
        return this;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<Date> getList() {
        return list;
    }

//    public List<DayPartTypes> getDayPartsList() {
//        return dayPartsList;
//    }
//
//    public void setDayPartsList(List<DayPartTypes> dayPartsList) {
//        this.dayPartsList = dayPartsList;
//    }

    
}
