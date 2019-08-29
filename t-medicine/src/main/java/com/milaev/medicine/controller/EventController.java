package com.milaev.medicine.controller;

import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.validators.EventValidator;
import com.milaev.medicine.service.EventService;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/event")
public class EventController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private EventService eventService;

    @Autowired
    EventValidator eventValidator;

    @InitBinder("dto")
    public void initBinderDateTime(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
        webDataBinder.setValidator(eventValidator);
    }

    @InitBinder("filter")
    public void initBinderFilter(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/list")
    public ModelAndView listEvents() {
        log.info("[/event] get request for url /list");
//        ModelAndView mav = eventService.getPreparedMAV();
//        mav.addObject("filter", new EventFilterDTO());
//        mav.addObject("dto", eventService.getAll());

        ModelAndView mav = prepareMAV(eventService.getAll());
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
        //return eventService.mavList();
    }

    @GetMapping(value = "/list/{insuranceId}")
    public ModelAndView listEvents(@PathVariable String insuranceId) {
        log.info("[/event] get request for url /list/{}", insuranceId);
//        ModelAndView mav = eventService.getPreparedMAV();
//        mav.addObject("filter", new EventFilterDTO());
//        mav.addObject("dto", eventService.getByInsuranceId(insuranceId));

        ModelAndView mav = prepareMAV(eventService.getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
        //return eventService.mavList();
    }

    @PostMapping(value = "/list")
    public ModelAndView listEvents(@ModelAttribute("filter") EventFilterDTO filter) {
        log.info("[/event] post request for url /list");
//        ModelAndView mav = eventService.getPreparedMAV();
//        mav.addObject("filter", filter);
//        mav.addObject("dto", eventService.getByFilter(filter));

        ModelAndView mav = prepareMAV(eventService.getByFilter(filter), filter);
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
        //return eventService.mavList(filter, result);
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id) {
        log.info("[/event] get request for url /edit/{}", id);
//        ModelAndView mav = eventService.getPreparedMAV();
//        mav.addObject("dto", eventService.getById(id));

        ModelAndView mav = prepareMAV(eventService.getById(id));
        return PageURLContext.getPage(mav, eventService.PAGE_REGISTRATION);
        //return eventService.mavEdit(id);
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id, @ModelAttribute("dto") @Validated EventDTO dto, BindingResult result) {
        log.info("[/event] get request for url /edit/{}", id);
//        ModelAndView mav = eventService.getPreparedMAV();
//        eventService.checkDTO(dto, result, mav);
//        eventService.updateProfile(dto);
//        mav.addObject("dto", dto);

        ModelAndView mav = prepareMAV(dto, null, result);
        eventService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, eventService.URI_LIST);
        //return eventService.mavEdit(dto, resul);
    }

    private ModelAndView prepareMAV(Object dto) {
        return prepareMAV(dto, null);
    }

    private ModelAndView prepareMAV(Object dto, EventFilterDTO filter) {
        return prepareMAV(dto, filter, null);
    }

    private ModelAndView prepareMAV(Object dto, EventFilterDTO filter, BindingResult result) {
        ModelAndView mav = eventService.getPreparedMAV();
        if (result != null)
            eventService.checkDTO((EventDTO) dto, result, mav);
        mav.addObject("dto", dto);
        if (filter == null)
            filter = new EventFilterDTO();
        mav.addObject("filter", filter);

        return mav;
    }
}
