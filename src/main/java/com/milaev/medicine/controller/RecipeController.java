package com.milaev.medicine.controller;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.service.interfaces.RecipeServiceInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    RecipeServiceInterface recipeService;

    @GetMapping(value = "/list/{insuranceId}") // , method = RequestMethod.GET
    public String listPatients(@PathVariable String insuranceId, ModelMap model) {
        log.info("listPatients()");
        String loggedinuser = sessionAuth.getUserName();
        RecipeDTO recipe = recipeService.getByInsuranceId(insuranceId);
        List<RecipeDTO> list = new ArrayList<>();
        list.add(recipe);

        //RecipeDTO recipe = recipeService.getByInsuranceId(insuranceId);


        model.addAttribute("recipes", list);
        model.addAttribute("insuranceId", insuranceId);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/list";
    }
}
