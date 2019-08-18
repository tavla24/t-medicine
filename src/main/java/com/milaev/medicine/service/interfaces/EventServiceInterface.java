package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface EventServiceInterface {

    String PAGE_LIST = "event/list";
    String PAGE_REGISTRATION = "event/registration";

    String URI_LIST = "/event/list";
    String URI_MAIN = "/";

    ModelAndView mavList();

    ModelAndView mavList(EventFilterDTO filter, BindingResult result);

    ModelAndView mavEdit(Long id);

    ModelAndView mavEdit(EventDTO dto, BindingResult result);

    List<EventDTO> getAll();

    List<EventDTO> getByFilter(EventFilterDTO filter);

    List<EventDTO> getByRecipeId(Long id);

    List<EventDTO> getByInsuranceId(String insuranceId);

    EventDTO getById(Long id);

    void updateEvents(Long recipeId);

    void updateEvents(RecipeSimpleDTO dto);

    void updateProfile(EventDTO dto);

    void delete(EventDTO dto);

//    void insert(EventDTO acc);
//
//    void update(EventDTO acc);
}
