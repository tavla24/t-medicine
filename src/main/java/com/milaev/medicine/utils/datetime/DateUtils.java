package com.milaev.medicine.utils.datetime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils {

    public static Date asDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate asLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDateTime asLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date setTime(Date date, int hour, int minute) {
        LocalDateTime ldt = asLocalDateTime(date);
        ldt = setTime(ldt, hour, minute);
        return asDate(ldt);
    }

    public static Date setTimeInc(Date date, int hour, int minute) {
        LocalDateTime ldt = asLocalDateTime(date);
        ldt = setTimeInc(ldt, hour, minute);
        return asDate(ldt);
    }
    
    public static Date dropTime(Date date) {
        return setTime(date, 0, 0);
    }

    public static LocalDateTime setTime(LocalDateTime dateTime, int hour, int minute) {
        return dateTime.withHour(hour).withMinute(minute);
    }

    public static LocalDateTime setTimeInc(LocalDateTime dateTime, int hour, int minute) {
        return dateTime.plusHours(hour).plusMinutes(minute);
    }
    
    public static LocalDateTime dropTime(LocalDateTime dateTime) {
        return setTime(dateTime, 0, 0);
    }
}