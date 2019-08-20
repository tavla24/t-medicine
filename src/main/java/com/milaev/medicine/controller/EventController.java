package com.milaev.medicine.controller;

import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.validators.EventValidator;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
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
    private EventServiceInterface eventService;

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
        return eventService.mavList();
    }

    @GetMapping(value = "/list/{insuranceId}")
    public ModelAndView listEvents(@PathVariable String insuranceId) {
        log.info("[/event] get request for url /list/{}", insuranceId);
        return eventService.mavList();
    }

    @PostMapping(value = "/list")
    public ModelAndView listEvents(@ModelAttribute("filter") EventFilterDTO filter, BindingResult result) {
        log.info("[/event] post request for url /list");
        return eventService.mavList(filter, result);
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id) {
        log.info("[/event] get request for url /edit/{}", id);
        return eventService.mavEdit(id);
    }

    @PostMapping(value = "/edit/{id}")
    public ModelAndView editEvent(@PathVariable Long id, @ModelAttribute("dto") @Validated EventDTO dto, BindingResult resul) {
        log.info("[/event] get request for url /edit/{}", id);
        return eventService.mavEdit(dto, resul);
    }
}
