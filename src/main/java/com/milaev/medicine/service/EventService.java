package com.milaev.medicine.service;

import com.milaev.medicine.dao.EventDAO;
import com.milaev.medicine.dao.RecipeSimpleDAO;
import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.dto.assistants.EventFilterDTOAssistant;
import com.milaev.medicine.dto.assistants.RecipeSimpleDTOAssistant;
import com.milaev.medicine.model.Event;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.model.enums.EventStatus;
import com.milaev.medicine.service.exceptions.EventValidationException;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import com.milaev.medicine.utils.PageURLContext;
import com.milaev.medicine.utils.datetime.DayOfWeekContainer;
import com.milaev.medicine.utils.datetime.DaysOfWeekContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EventService extends AbstractService implements EventServiceInterface {

    private static Logger log = LoggerFactory.getLogger(EventService.class);

    @Autowired
    private EventDAO daoEvent;

    @Autowired
    private RecipeSimpleDAO daoRecipeSimple;

    @Autowired
    EventFilterDTOAssistant eventFilterDTOAssistant;

    @Autowired
    RecipeSimpleDTOAssistant recipeSimpleDTOAssistant;

    @Override
    @Transactional
    public ModelAndView mavList() {
        log.info("called EventService.mavList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("filter", new EventFilterDTO());
        mav.addObject("dto", getAll());
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavList(String insuranceId) {
        log.info("called EventService.mavList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("filter", new EventFilterDTO());
        mav.addObject("dto", getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavList(EventFilterDTO filter, BindingResult result) {
        log.info("called EventService.mavList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("filter", filter);
        mav.addObject("dto", getByFilter(filter));
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(Long id) {
        log.info("called EventService.mavEdit");
        ModelAndView mav = getPreparedMAV();
        //mav.addObject("statuses", EventStatus.getStatusList());
        mav.addObject("dto", getById(id));
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Override
    @Transactional
    public ModelAndView mavEdit(EventDTO dto, BindingResult result) {
        log.info("called EventService.mavEdit with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        mav.addObject("dto", dto);
        return PageURLContext.getPageRedirect(mav, URI_LIST);
    }

    private void checkDTO(EventDTO dto, BindingResult result,
                          ModelAndView mav) {
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new EventValidationException(dto, result, mav);
        }
    }

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

    @Override
    @Transactional
    public List<EventDTO> getByFilter(EventFilterDTO filter) {
        eventFilterDTOAssistant.createQuery(filter);
        List<Event> list = daoEvent.getByFilter(eventFilterDTOAssistant.getQueryString(), eventFilterDTOAssistant.getQueryParams());
        return fillDTO(list);
    }

    @Override
    @Transactional
    public boolean isAllEventsDone(String insuranceId) {
        List<EventDTO> list = getByInsuranceId(insuranceId);
        for (EventDTO item : list)
            if (item.getStatus().equals(EventStatus.PLAN.name()))
                return false;
        return true;
    }

    private List<EventDTO> fillDTO(List<Event> dbList) {
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
        if (recipeId == null)
            return;

        RecipeSimple recipe = daoRecipeSimple.getById(recipeId);
        RecipeSimpleDTO recipeDTO = new RecipeSimpleDTO();
        MapperUtil.toDTORecipeSimple().accept(recipe, recipeDTO);
        updateEvents(recipeDTO);
    }

    @Override
    @Transactional
    public void updateEvents(RecipeSimpleDTO dto) {
        if (dto.getId() == null)
            return;

        deleteChangedEvents(dto);

        if (!dto.isHealthful()) {
            createChangedEvents(dto);
        }
    }

    private void deleteChangedEvents(RecipeSimpleDTO dto) {
        List<EventDTO> eventsDTOList = getByRecipeId(dto.getId());
        for (EventDTO item : eventsDTOList)
            //if (item.getDatestamp().after(new Date()))
            if (item.getStatus().equals(EventStatus.PLAN.name()))
                delete(item);
    }

    private void createChangedEvents(RecipeSimpleDTO dto) {
        DaysOfWeekContainer dowc = new DaysOfWeekContainer();
        Date startDate = new Date();
        startDate = (dto.getDateFrom().after(startDate)) ? dto.getDateFrom() : startDate;
        dowc.fill(startDate, dto.getDateTo(), dto.getDayOfWeekList(), dto.getPartOfDayList());

        for (DayOfWeekContainer item : dowc.getList()) {
            List<Date> dayList = item.getList();
            for (Date date : dayList) {
                EventDTO eventDTO = new EventDTO();
                eventDTO.setRecipe(dto);
                eventDTO.setDatestamp(date);
                eventDTO.setStatus(EventStatus.PLAN.name());
                updateProfile(eventDTO);
            }
        }
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
        log.info("fillDTODataToEntity [{}]", a.getDoze());
        MapperUtil.toEntityEvent().accept(dto, db);
    }
}
