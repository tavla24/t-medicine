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
        EventFilterDTO filter = new EventFilterDTO();
        ModelAndView mav = prepareMAV(eventService.getByFilter(filter), filter);
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
    }

    @GetMapping(value = "/list/{insuranceId}")
    public ModelAndView listEvents(@PathVariable String insuranceId) {
        log.info("[/event] get request for url /list/{}", insuranceId);
        ModelAndView mav = prepareMAV(eventService.getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
    }

    @PostMapping(value = "/list")
    public ModelAndView listEvents(@ModelAttribute("filter") EventFilterDTO filter) {
        log.info("[/event] post request for url /list");
        ModelAndView mav = prepareMAV(eventService.getByFilter(filter), filter);
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
    }

    @PostMapping(value = "/list/prev")
    public ModelAndView listEventsPrev(@ModelAttribute("filter") EventFilterDTO filter) {
        log.info("[/event] get request for url /list/prev");
        filter.getNavigation().setPrev(true);
        ModelAndView mav = prepareMAV(eventService.getByFilter(filter), filter);
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
    }

    @PostMapping(value = "/list/next")
    public ModelAndView listEventsNext(@ModelAttribute("filter") EventFilterDTO filter) {
        log.info("[/event] get request for url /list/next");
        filter.getNavigation().setNext(true);
        ModelAndView mav = prepareMAV(eventService.getByFilter(filter), filter);
        return PageURLContext.getPage(mav, eventService.PAGE_LIST);
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id) {
        log.info("[/event] get request for url /edit/{}", id);
        ModelAndView mav = prepareMAV(eventService.getById(id));
        return PageURLContext.getPage(mav, eventService.PAGE_REGISTRATION);
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id, @ModelAttribute("dto") @Validated EventDTO dto, BindingResult result) {
        log.info("[/event] get request for url /edit/{}", id);
        ModelAndView mav = prepareMAV(dto, null, result);
        eventService.updateProfile(dto);
        eventService.sendMessageBoard();
        return PageURLContext.getPageRedirect(mav, eventService.URI_LIST);
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
