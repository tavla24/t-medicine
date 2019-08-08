package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeServiceInterface {

    List<RecipeDTO> getAll();

    RecipeDTO getByInsuranceId(String insuranceId);

    RecipeDTO getById(Long id);

    void insert(RecipeDTO acc);

    void delete(String insuranceId);

    void update(RecipeDTO acc);
}
