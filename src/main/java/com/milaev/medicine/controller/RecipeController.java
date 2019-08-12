package com.milaev.medicine.controller;


import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.dto.DTOContainer;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayNameTypes;
import com.milaev.medicine.model.enums.DayPartTypes;
import com.milaev.medicine.model.enums.HealingType;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import com.milaev.medicine.service.interfaces.RecipeSimpleServiceInterface;
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

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    PatientServiceInterface patientService;

    @Autowired
    private EventServiceInterface eventService;


    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping(value = "/list/{insuranceId}") // , method = RequestMethod.GET
    public String listPatients(@PathVariable String insuranceId, ModelMap model) {
        log.info("listPatients()");
        String loggedinuser = sessionAuth.getUserName();
        List<RecipeSimpleDTO> dtoList = recipeService.getByInsuranceId(insuranceId);
        //DTOContainer<RecipeSimpleDTO> container = new DTOContainer(list);

        //RecipeDTO recipe = recipeService.getByInsuranceId(insuranceId);

        model.addAttribute("dto", dtoList);
        model.addAttribute("insuranceId", insuranceId);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/list_simple";
    }

    @GetMapping(value = "/new/{insuranceId}") // , method = RequestMethod.GET
    public String newRecipe(@PathVariable String insuranceId, ModelMap model) {
        log.info("newRecipe()");

        String loggedinuser = sessionAuth.getUserName();

        PatientDTO patientDTO = patientService.getByInsuranceId(insuranceId);
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        dto.setPatient(patientDTO);

        List<String> sourceHealingTypes = HealingType.getTypeList();
        List<String> sourceDayNames = DayNameTypes.getTypeList();
        List<String> sourceDayParts = DayPartTypes.getTypeList();

        model.addAttribute("sourceHealingTypes", sourceHealingTypes);
        model.addAttribute("sourceDayNames", sourceDayNames);
        model.addAttribute("sourceDayParts", sourceDayParts);
        model.addAttribute("dto", dto);
        model.addAttribute("id", 0);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/registration_simple";
    }

    @GetMapping(value = "/edit/{insuranceId}/{id}") // , method = RequestMethod.GET
    public String editRecipe(@PathVariable String insuranceId, @PathVariable Long id, ModelMap model) {
        log.info("editRecipes()");
//        Long recipeId = 0L;
//
//        try {
//            recipeId = Long.parseLong(id);
//        }catch (Exception ex){
//            System.out.println("parseLong failed!");
//            ex.printStackTrace();
//        }

        String loggedinuser = sessionAuth.getUserName();

        PatientDTO patientDTO = patientService.getByInsuranceId(insuranceId);
        RecipeSimpleDTO dto = recipeService.getById(id);
        //dto.convToDayNamesList();
        //dto.convToDayPartsList();
        dto.setPatient(patientDTO);
        //dto.translate();

        List<String> sourceHealingTypes = HealingType.getTypeList();
        List<String> sourceDayNames = DayNameTypes.getTypeList();
        List<String> sourceDayParts = DayPartTypes.getTypeList();

        model.addAttribute("sourceHealingTypes", sourceHealingTypes);
        model.addAttribute("sourceDayNames", sourceDayNames);
        model.addAttribute("sourceDayParts", sourceDayParts);
        model.addAttribute("dto", dto);
        model.addAttribute("id", id);
        model.addAttribute("loggedinuser", sessionAuth.getUserName());
        return "recipe/registration_simple";
    }

    @PostMapping(value = "/edit/{insuranceId}/{id}") // , method = RequestMethod.GET @PathVariable String id,
    //@PostMapping(value = "/edit") // , method = RequestMethod.GET
    public String editRecipePost(@PathVariable String insuranceId, @PathVariable Long id, @Valid RecipeSimpleDTO dto, BindingResult result, ModelMap model) {
        log.info("editRecipesPost()");
        recipeService.updateProfile(dto);
//        eventService.updateEvents(dto.getId());
        return String.format("redirect:/recipe/list/%s", dto.getPatient().getInsuranceId());
    }

    @GetMapping(value = "/delete/{insuranceId}/{id}") // , method = RequestMethod.GET
    public String deleteRecipe(@Valid RecipeSimpleDTO dto, @PathVariable String insuranceId, @PathVariable String id, BindingResult result, ModelMap model) {
        log.info("deleteRecipes()");
        dto.getPatient().setInsuranceId(insuranceId);
        //dto.setId(id);
        recipeService.delete(dto);
        return String.format("redirect:/recipe/list/%s", insuranceId);
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
