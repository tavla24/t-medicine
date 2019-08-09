package com.milaev.medicine.service;

import com.milaev.medicine.dao.EventDAO;
import com.milaev.medicine.dao.RecipeSimpleDAO;
import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.Event;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import com.milaev.medicine.utils.datetime.DayOfWeekContainer;
import com.milaev.medicine.utils.datetime.DaysOfWeekContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService implements EventServiceInterface {

    private static Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventDAO daoEvent;

    @Autowired
    private RecipeSimpleDAO daoRecipeSimple;

    @Override
    @Transactional
    public List<EventDTO> getAll() {
        List<Event> list = daoEvent.getAll();
        return fillDTO(list);
    }

    @Override
    @Transactional
    public List<EventDTO> getByRecipeId(Long id) {
        List<Event> list = daoEvent.getByRecipeId(id);
        return fillDTO(list);
    }

    @Override
    @Transactional
    public List<EventDTO> getByInsuranceId(String insuranceId) {
        List<Event> list = daoEvent.getByInsuranceId(insuranceId);
        return fillDTO(list);
    }

    private List<EventDTO> fillDTO(List<Event> dbList){
        List<EventDTO> listDTO = new ArrayList<>();
        for (Event item : dbList) {
            EventDTO dto = new EventDTO();
            MapperUtil.toDTOEvent().accept(item, dto);
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    @Transactional
    public EventDTO getById(Long id) {
        Event db = daoEvent.getById(id);
        EventDTO dto = new EventDTO();
        if (db != null)
            MapperUtil.toDTOEvent().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public void delete(EventDTO dto) {
        Event db = daoEvent.getById(dto.getId());
        try {
            daoEvent.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateEvents(Long recipeId) {
        RecipeSimple recipe = daoRecipeSimple.getById(recipeId);
        RecipeSimpleDTO recipeDTO = new RecipeSimpleDTO();
        MapperUtil.toDTORecipeSimple().accept(recipe, recipeDTO);
        updateEvents(recipeDTO);
    }

    @Override
    @Transactional
    public void updateEvents(RecipeSimpleDTO dto) {
        List<EventDTO> eventsDTOList = getByRecipeId(dto.getId());
        for (EventDTO item: eventsDTOList)
            if (item.getDatestamp().before(new Date()))
                delete(item);

        DaysOfWeekContainer dowc = new DaysOfWeekContainer();
        //dowc.fill(dto.getDateFrom(), dto.getDateTo(), dto.getDayNamesList());

        for (DayOfWeekContainer item : dowc.getList())
            System.out.println(item.getList());
    }

    @Override
    @Transactional
    public void updateProfile(EventDTO dto) {
        log.info("service.updateProfile(Event) id [{}]", dto.getId());
        //RecipeSimple db = daoRecipeSimple.getById(dto.getId());

        if (dto.getId() == null)
            insert(dto, new Event());
        else
            update(dto, daoEvent.getById(dto.getId()));
    }

    private void insert(EventDTO dto, Event db) {
        log.info("service.insert(Event)");
        fillDTODataToEntity(dto, db);
        try {
            daoEvent.insert(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void update(EventDTO dto, Event db) {
        log.info("service.insert(Event)");
        fillDTODataToEntity(dto, db);
        try {
            daoEvent.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void fillDTODataToEntity(EventDTO dto, Event db) {
        log.info("fillDTODataToEntity");
        RecipeSimple a = daoRecipeSimple.getById(dto.getRecipe().getId());
        db.setRecipe(a);
        MapperUtil.toEntityEvent().accept(dto, db);
    }
}
