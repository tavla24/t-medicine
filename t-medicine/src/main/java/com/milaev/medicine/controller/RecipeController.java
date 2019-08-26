package com.milaev.medicine.controller;


import com.milaev.medicine.dto.DTOContainer;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.dto.validators.RecipeSimpleValidator;
import com.milaev.medicine.service.interfaces.RecipeSimpleServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeSimpleServiceInterface recipeService;

    @Autowired
    RecipeSimpleValidator recipeSimpleValidator;

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

        webDataBinder.setValidator(recipeSimpleValidator);
    }

    @GetMapping(value = "/list/{insuranceId}")
    public ModelAndView listRecipes(@PathVariable String insuranceId) {
        log.info("[/recipe] get request for url /list/{}", insuranceId);
        return recipeService.mavList(insuranceId);
    }

    @GetMapping(value = "/new/{insuranceId}")
    public ModelAndView newRecipe(@PathVariable String insuranceId) {
        log.info("[/recipe] get request for url /new/{}", insuranceId);
        return recipeService.mavNew(insuranceId);
    }

    @PostMapping(value = "/new/{insuranceId}")
    public ModelAndView newRecipe(@PathVariable String insuranceId, @Validated RecipeSimpleDTO dto, BindingResult result) {
        log.info("[/recipe] post request for url /new/{}", insuranceId);
        return recipeService.mavNew(dto, result);
    }

    @GetMapping(value = "/edit/{insuranceId}/{id}")
    public ModelAndView editRecipe(@PathVariable String insuranceId, @PathVariable Long id) {
        log.info("[/recipe] get request for url /edit/{}/{}", insuranceId, id);
        return recipeService.mavEdit(insuranceId, id);
    }

    @PostMapping(value = "/edit/{insuranceId}/{id}")
    public ModelAndView editRecipe(@PathVariable String insuranceId, @PathVariable Long id, @Validated RecipeSimpleDTO dto, BindingResult result) {
        log.info("[/recipe] post request for url /edit/{}/{}", insuranceId, id);
        return recipeService.mavEdit(dto, result);
    }

    @GetMapping(value = "/delete/{insuranceId}/{id}") // , method = RequestMethod.GET
    public ModelAndView deleteRecipe(@Valid RecipeSimpleDTO dto, @PathVariable String insuranceId, @PathVariable String id) {
        log.info("[/recipe] get request for url /delete/{}/{}", insuranceId, id);
        return recipeService.mavDelete(dto, insuranceId);
    }


    // TODO delete by post + fill model
    @PostMapping(value = "/list/{insuranceId}") // , method = RequestMethod.GET @Valid @ModelAttribute("container") @Valid @ModelAttribute("container")
    public String deleteRecipeDel(DTOContainer<RecipeSimpleDTO> dto, BindingResult result, ModelMap model) {
        log.info("deleteRecipesDel()");
        //dto.getPatient().setInsuranceId(insuranceId);
        //dto.setId(id);
        //recipeService.delete(dto);
        return String.format("redirect:/recipe/list/0");
    }
}
