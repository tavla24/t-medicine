package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeSimpleServiceInterface {

    List<RecipeSimpleDTO> getAll();

    RecipeSimpleDTO getByInsuranceId(String insuranceId);

    RecipeSimpleDTO getById(Long id);

    public void updateProfile(RecipeSimpleDTO dto);

    void delete(RecipeSimpleDTO dto);

//    void insert(RecipeSimpleDTO acc);
//
//    void update(RecipeSimpleDTO acc);
}
