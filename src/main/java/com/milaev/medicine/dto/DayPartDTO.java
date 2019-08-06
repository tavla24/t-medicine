package com.milaev.medicine.dto;

import java.util.Date;

public class DayPartDTO {
    private String part;
    private Date time;
    private String doze;
    //private DayNameDTO dayName;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getDoze() {
        return doze;
    }

    public void setDoze(String doze) {
        this.doze = doze;
    }

//    public DayNameDTO getDayName() {
//        if (dayName == null)
//            dayName = new DayNameDTO();
//        return dayName;
//    }
//
//    public void setDayName(DayNameDTO dayName) {
//        this.dayName = dayName;
//    }

}
