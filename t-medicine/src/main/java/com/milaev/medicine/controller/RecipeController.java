package com.milaev.medicine.controller;


import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.dto.validators.RecipeSimpleValidator;
import com.milaev.medicine.service.RecipeSimpleService;
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
@RequestMapping("/recipe")
public class RecipeController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private RecipeSimpleService recipeService;

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
        ModelAndView mav = recipeService.getPreparedMAV();
        mav.addObject("dto", recipeService.getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, recipeService.PAGE_LIST);
    }

    @GetMapping(value = "/new/{insuranceId}")
    public ModelAndView newRecipe(@PathVariable String insuranceId) {
        log.info("[/recipe] get request for url /new/{}", insuranceId);
        ModelAndView mav = recipeService.getPreparedMAV();
        RecipeSimpleDTO dto = recipeService.getRecipeSimpleDTOwithPatient(insuranceId);
        mav.addObject("dto", dto);
        return PageURLContext.getPage(mav, recipeService.PAGE_REGISTRATION);
    }

    @PostMapping(value = "/new/{insuranceId}")
    public ModelAndView newRecipe(@PathVariable String insuranceId, @Validated RecipeSimpleDTO dto, BindingResult result) {
        log.info("[/recipe] post request for url /new/{}", insuranceId);
        ModelAndView mav = recipeService.getPreparedMAV();
        recipeService.checkDTO(dto, result, mav);
        recipeService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, String.format("%s/%s", recipeService.URI_LIST, dto.getPatient().getInsuranceId()));
    }

    @GetMapping(value = "/edit/{insuranceId}/{id}")
    public ModelAndView editRecipe(@PathVariable String insuranceId, @PathVariable Long id) {
        log.info("[/recipe] get request for url /edit/{}/{}", insuranceId, id);
        ModelAndView mav = recipeService.getPreparedMAV();
        RecipeSimpleDTO dto = recipeService.getById(id);
        mav.addObject("dto", dto);
        return PageURLContext.getPage(mav, recipeService.PAGE_REGISTRATION);
    }

    @PostMapping(value = "/edit/{insuranceId}/{id}")
    public ModelAndView editRecipe(@PathVariable String insuranceId, @PathVariable Long id, @Validated RecipeSimpleDTO dto, BindingResult result) {
        log.info("[/recipe] post request for url /edit/{}/{}", insuranceId, id);
        ModelAndView mav = recipeService.getPreparedMAV();
        recipeService.checkDTO(dto, result, mav);
        recipeService.updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, String.format("%s/%s", recipeService.URI_LIST, dto.getPatient().getInsuranceId()));
    }

    @GetMapping(value = "/delete/{insuranceId}/{id}")
    public ModelAndView deleteRecipe(RecipeSimpleDTO dto, @PathVariable String insuranceId, @PathVariable String id) {
        log.info("[/recipe] get request for url /delete/{}/{}", insuranceId, id);
        dto.getPatient().setInsuranceId(insuranceId);
        recipeService.delete(dto);
        return PageURLContext.getPageRedirect(new ModelAndView(),String.format("%s/%s", recipeService.URI_LIST, dto.getPatient().getInsuranceId()));
    }

}
