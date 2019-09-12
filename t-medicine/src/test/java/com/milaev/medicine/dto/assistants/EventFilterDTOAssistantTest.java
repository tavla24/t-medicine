package com.milaev.medicine.dto.assistants;

import com.milaev.medicine.dto.EventFilterDTO;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class EventFilterDTOAssistantTest {

    EventFilterDTOAssistant filter = new EventFilterDTOAssistant();

    @Test
    public void getFilterOrderByTime() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setSortByTime(true);

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f order by f.datestamp", filter.getQueryString());
        Assert.assertEquals(0, filter.getQueryParams().size());
    }

    @Test
    public void getFilterName() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setName("name");

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.recipe.patient.name LIKE :p_name", filter.getQueryString());
        Assert.assertEquals(1, filter.getQueryParams().size());
    }

    @Test
    public void getFilterSurname() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setSurname("surname");

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.recipe.patient.surname LIKE :p_surname", filter.getQueryString());
        Assert.assertEquals(1, filter.getQueryParams().size());
    }

    @Test
    public void getFilterNextHours() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setNextHours(2);

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.datestamp > :p_date_now and f.datestamp < :p_date_next", filter.getQueryString());
        Assert.assertEquals(2, filter.getQueryParams().size());
    }

    @Test
    public void getFilterNextHoursOverrideDate() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setDateFrom(new Date());
        dto.setDateTo(new Date());
        dto.setNextHours(2);

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.datestamp > :p_date_now and f.datestamp < :p_date_next", filter.getQueryString());
        Assert.assertEquals(2, filter.getQueryParams().size());
    }

    @Test
    public void getFilterDateFromTo() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setDateFrom(new Date());
        dto.setDateTo(new Date());

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.datestamp > :p_date_from and f.datestamp < :p_date_to", filter.getQueryString());
        Assert.assertEquals(2, filter.getQueryParams().size());
    }

    @Test
    public void getFilterStatus() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setStatus("status");

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.status = :p_status", filter.getQueryString());
        Assert.assertEquals(1, filter.getQueryParams().size());
    }

    @Test
    public void getFilterHealingType() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setHealingType("type");

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.recipe.healingType = :p_healing_type", filter.getQueryString());
        Assert.assertEquals(1, filter.getQueryParams().size());
    }

    @Test
    public void getFilterAll() {
        EventFilterDTO dto = new EventFilterDTO();
        dto.setSortByTime(true);
        dto.setName("name");
        dto.setSurname("surname");
        dto.setNextHours(2);
        dto.setDateFrom(new Date());
        dto.setDateTo(new Date());
        dto.setStatus("status");
        dto.setHealingType("type");

        filter.createQuery(dto, false);
        Assert.assertEquals("from Event as f where f.recipe.patient.name LIKE :p_name and f.recipe.patient.surname LIKE :p_surname and f.datestamp > :p_date_now and f.datestamp < :p_date_next and f.status = :p_status and f.recipe.healingType = :p_healing_type order by f.datestamp", filter.getQueryString());
        Assert.assertEquals(6, filter.getQueryParams().size());
    }
}
