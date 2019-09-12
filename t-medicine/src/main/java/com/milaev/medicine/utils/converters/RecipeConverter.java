package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.utils.MapperUtil;

public class RecipeConverter {
    public static RecipeSimpleDTO toDTO(RecipeSimple db){
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        MapperUtil.toDTORecipeSimple().accept(db, dto);
        //dto.getDoctor().setLogin(db.getDoctor().getAccount().getLogin());
        dto.setPatient(PatientConverter.toDTO(db.getPatient()));
//        dto.setHealthful(dto.getPatient().getStatus().equals(PatientStatus.HEALTHY.name()));
        return dto;
    }

    public static RecipeSimple toEntity(RecipeSimpleDTO dto, RecipeSimple db){
        MapperUtil.toEntityRecipeSimple().accept(dto, db);
        return db;
    }
}
