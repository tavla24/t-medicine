package com.milaev.medicine.dto.assistants;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayPartTypes;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class RecipeSimpleDTOAssistant {

    public static final String SPLITTER = ";";

    private List<DayOfWeek> dayOfWeekList;
    private List<DayPartTypes> partOfDayList;

    RecipeSimpleDTO recipeSimpleDTO;

    public void setDTO(RecipeSimpleDTO recipeSimpleDTO){
        this.recipeSimpleDTO = recipeSimpleDTO;
    }

    public void createString(){
        convListToDayNames();
        convListToDayParts();
    }

    public void createList(){
        convToDayNamesList();
        convToDayPartsList();
    }

    private void convToDayNamesList() {
        recipeSimpleDTO.setDayNamesList(new ArrayList<>());
        if (recipeSimpleDTO.getDayNames() != null)
            recipeSimpleDTO.setDayNamesList(Arrays.asList(recipeSimpleDTO.getDayNames().split(SPLITTER)));
    }

    private void convToDayPartsList() {
        recipeSimpleDTO.setDayPartsList(new ArrayList<>());
        if (recipeSimpleDTO.getDayParts() != null)
            recipeSimpleDTO.setDayPartsList(Arrays.asList(recipeSimpleDTO.getDayParts().split(SPLITTER)));
    }

    private void convListToDayNames() {
        StringBuilder sb = new StringBuilder();
        for (String item : recipeSimpleDTO.getDayNamesList())
            sb.append(String.format("%s%s", item, SPLITTER));
        recipeSimpleDTO.setDayNames(sb.toString());
    }

    private void convListToDayParts() {
        StringBuilder sb = new StringBuilder();
        for (String item : recipeSimpleDTO.getDayPartsList())
            sb.append(String.format("%s%s", item, SPLITTER));
        recipeSimpleDTO.setDayParts(sb.toString());
    }

    public List<DayOfWeek> getDayOfWeekList() {
        convToDayNamesList();
        dayOfWeekList = new ArrayList<>();
        for (DayOfWeek itemDOW : DayOfWeek.values())
            for (String itemS : recipeSimpleDTO.getDayNamesList())
                if (itemDOW.name().toLowerCase().equals(itemS.toLowerCase()))
                    dayOfWeekList.add(itemDOW);

        return dayOfWeekList;
    }

    public List<DayPartTypes> getPartOfDayList() {
        convToDayPartsList();
        partOfDayList = new ArrayList<>();
        for (DayPartTypes itemDPT : DayPartTypes.values())
            for (String itemS : recipeSimpleDTO.getDayPartsList())
                if (itemDPT.name().toLowerCase().equals(itemS.toLowerCase()))
                    partOfDayList.add(itemDPT);

        return partOfDayList;
    }
}
