package com.milaev.medicine.controller;


import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.DTOContainer;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayNameTypes;
import com.milaev.medicine.model.enums.DayPartTypes;
import com.milaev.medicine.model.enums.HealingType;
import com.milaev.medicine.service.interfaces.RecipeServiceInterface;
import com.milaev.medicine.service.interfaces.RecipeSimpleServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

    private static Logger log = LoggerFactory.getLogger(RecipeController.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    @Autowired
    MessageSource messageSource;

    @Autowired
    RecipeSimpleServiceInterface recipeService;

    @GetMapping(value = "/list/{insuranceId}") // , method = RequestMethod.GET
    public String listPatients(@PathVariable String insuranceId, ModelMap model) {
        log.info("listPatients()");
        String loggedinuser = sessionAuth.getUserName();
        RecipeSimpleDTO recipe = recipeService.getByInsuranceId(insuranceId);
        List<RecipeSimpleDTO> list = new ArrayList<>();
        list.add(recipe);
        DTOContainer<RecipeSimpleDTO> container = new DTOContainer(list);

        //RecipeDTO recipe = recipeService.getByInsuranceId(insuranceId);

        model.addAttribute("container", container);
        model.addAttribute("insuranceId", insuranceId);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/list_simple2";
    }

    @GetMapping(value = "/edit/{id}") // , method = RequestMethod.GET
    public String editRecipes(@PathVariable String id, ModelMap model) {
        log.info("editRecipes()");
        Long recipeId = 0L;

        try {
            recipeId = Long.parseLong(id);
        }catch (Exception ex){
            System.out.println("parseLong failed!");
            ex.printStackTrace();
        }

        String loggedinuser = sessionAuth.getUserName();
        RecipeSimpleDTO dto = recipeService.getById(recipeId);
        dto.convToDayNamesList();
        dto.convToDayPartsList();
        //dto.translate();

        List<String> sourceHealingTypes = HealingType.getTypeList();
        List<String> sourceDayNames = DayNameTypes.getTypeList();
        List<String> sourceDayParts = DayPartTypes.getTypeList();

        model.addAttribute("sourceHealingTypes", sourceHealingTypes);
        model.addAttribute("sourceDayNames", sourceDayNames);
        model.addAttribute("sourceDayParts", sourceDayParts);
        model.addAttribute("recipes", dto);
        model.addAttribute("id", recipeId);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/registration_simple";
    }

    @PostMapping(value = "/edit/{id}") // , method = RequestMethod.GET
    public String editRecipesPost(@Valid RecipeSimpleDTO dto, @PathVariable String id, BindingResult result, ModelMap model) {
        log.info("editRecipesPost()");
        recipeService.updateProfile(dto);
        return String.format("recipe/list/%d", dto.getPatient().getInsuranceId());
    }

    @GetMapping(value = "/delete/{insuranceId}/{id}") // , method = RequestMethod.GET
    public String deleteRecipes(@Valid RecipeSimpleDTO dto, @PathVariable String insuranceId, @PathVariable String id, BindingResult result, ModelMap model) {
        log.info("deleteRecipes()");
        dto.getPatient().setInsuranceId(insuranceId);
        //dto.setId(id);
        recipeService.delete(dto);
        return String.format("redirect:/recipe/list/%s", insuranceId);
    }

    @PostMapping(value = "/list/{insuranceId}") // , method = RequestMethod.GET @Valid @ModelAttribute("container") @Valid @ModelAttribute("container")
    public String deleteRecipesDel(DTOContainer<RecipeSimpleDTO> dto, BindingResult result, ModelMap model) {
        log.info("deleteRecipesDel()");
        //dto.getPatient().setInsuranceId(insuranceId);
        //dto.setId(id);
        //recipeService.delete(dto);
        return String.format("redirect:/recipe/list/0");
    }
}
