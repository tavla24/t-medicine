package com.milaev.medicine.utils.datetime;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayOfWeekContainer {
    private DayOfWeek dayOfWeek;
    private List<Date> list = new ArrayList<>();

    public DayOfWeekContainer(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public DayOfWeekContainer fill(Date from, Date to) {
        LocalDateTime ldFrom = DateUtils.asLocalDateTime(from);
        LocalDateTime ldTo = DateUtils.asLocalDateTime(to);

        for (LocalDateTime date = ldFrom; date.isBefore(ldTo) || date.isEqual(ldTo); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == (dayOfWeek))
                list.add(DateUtils.asDate(date));
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
}
