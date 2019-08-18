package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.model.enums.EventStatus;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/event")
public class EventController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private EventServiceInterface eventService;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/list") // , method = RequestMethod.GET
    public ModelAndView listEvents(ModelMap model) {
        log.info("[/event] get request for url /list");
        return eventService.mavList();
    }

    @PostMapping(value = "/list") // , method = RequestMethod.GET
    public ModelAndView listEvents(@ModelAttribute("filter") EventFilterDTO filter, BindingResult result) {
        log.info("[/event] post request for url /list");
        return eventService.mavList(filter, result);
    }

    @GetMapping(value = "/edit/{id}") // , method = RequestMethod.GET
    public String editEvent(@PathVariable Long id, ModelMap model) {
        log.info("editEvent()");
        String loggedinuser = sessionAuth.getUserName();
        EventDTO dto = eventService.getById(id);

        model.addAttribute("statuses", EventStatus.getStatusList());
        model.addAttribute("dto", dto);
        model.addAttribute("loggedinuser", loggedinuser);
        return "event/registration";
    }

    @PostMapping(value = "/edit/{id}") // , method = RequestMethod.GET
    public String editEventPost(@PathVariable Long id, EventDTO dto, ModelMap model) {
        log.info("editEvent()");
        String loggedinuser = sessionAuth.getUserName();
        //EventDTO dto = eventService.getById(id);

        dto.setId(id);
        eventService.updateProfile(dto);

        model.addAttribute("statuses", EventStatus.getStatusList());
        model.addAttribute("dto", dto);
        model.addAttribute("loggedinuser", loggedinuser);
        return "redirect:/event/list";
    }
}
