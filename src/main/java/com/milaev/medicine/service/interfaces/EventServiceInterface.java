package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.EventFilterDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;

import java.util.List;

public interface EventServiceInterface {

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
