package com.milaev.medicine.service;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayNameTypes;
import com.milaev.medicine.model.enums.DayPartTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class
})
@WebAppConfiguration
public class EventServiceTest {

    @Autowired
    EventService eventService;

    @Autowired
    RecipeSimpleService recipeService;

    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @Test
    public void testGetListAll() throws ParseException {
        eventService.getAll();
    }

    @Test
    public void testGetExchangeData() {
        eventService.getExchangeData();
    }

    @Test
    public void testGetById() {
        eventService.getById(0l);
    }

    @Test
    public void testGetByInsuranceId() {
        eventService.getByInsuranceId("FGR1458762");
    }

    @Test
    public void testGetByRecipeId() {
        eventService.getByRecipeId(0l);
    }

    @Test
    public void testGetByFilter() {
        EventFilterDTO filter = new EventFilterDTO();
        eventService.getByFilter(filter);
    }

    @Test
    public void testIsAllEventsDone() {
        List<RecipeSimpleDTO> list = recipeService.getByInsuranceId("FGR1458762");
        RecipeSimpleDTO recipe = list.get(0);
        eventService.isAllEventsDone(recipe.getPatient().getInsuranceId());
    }

    @Test
    public void testUpdateEvents() throws ParseException {
        List<RecipeSimpleDTO> list = recipeService.getByInsuranceId("FGR1458762");
        RecipeSimpleDTO recipe = list.get(0);
        recipe.setDateFrom(format.parse("01/01/2020"));
        recipe.setDateTo(format.parse("03/03/2020"));
        recipe.setDayNamesList(Arrays.asList(DayNameTypes.MONDAY.name(), DayNameTypes.THURSDAY.name()));
        recipe.setDayPartsList(Arrays.asList(DayPartTypes.MORNING.name(), DayPartTypes.EVENING.name()));
        eventService.updateEvents(recipe);
        eventService.updateEvents(recipe.getId());
    }

    @Test
    public void testDelete() {
        List<EventDTO> list = eventService.getAll();
        eventService.delete(list.get(0));
    }
}
