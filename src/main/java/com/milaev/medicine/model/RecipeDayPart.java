package com.milaev.medicine.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "recipes_day_parts")
public class RecipeDayPart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private long id;

    @Column(name = "part")
    private String dayPart; // = ""; //DayParts.MORNING.getDayPart();

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    @Column(name = "doze")
    private String doze;

    @ManyToOne
    @JoinColumn(name = "day_name_id")
    private RecipeDayNames dayNames;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDayPart() {
        return dayPart;
    }

    public void setDayPart(String dayPart) {
        this.dayPart = dayPart;
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

    public RecipeDayNames getDayNames() {
        return dayNames;
    }

    public void setDayNames(RecipeDayNames dayNames) {
        this.dayNames = dayNames;
    }
}
