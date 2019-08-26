package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public interface RecipeSimpleServiceInterface {

    String PAGE_LIST = "recipe/list_simple";
    String PAGE_REGISTRATION = "recipe/registration_simple";

    String URI_LIST = "/recipe/list";
    String URI_MAIN = "/";

    ModelAndView mavList(String insuranceId);

    ModelAndView mavNew(String insuranceId);

    ModelAndView mavNew(RecipeSimpleDTO dto, BindingResult result);

    ModelAndView mavDelete(RecipeSimpleDTO dto, String insuranceId);

    ModelAndView mavEdit(String insuranceId, Long id);

    ModelAndView mavEdit(RecipeSimpleDTO dto, BindingResult result);

    List<RecipeSimpleDTO> getAll();

    List<RecipeSimpleDTO> getByInsuranceId(String insuranceId);

    RecipeSimpleDTO getById(Long id);

    void updateProfile(RecipeSimpleDTO dto);

    void delete(RecipeSimpleDTO dto);

//    void insert(RecipeSimpleDTO acc);
//
//    void update(RecipeSimpleDTO acc);
}
