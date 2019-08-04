package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeServiceInterface {

    List<RecipeDTO> getAll();

    RecipeDTO getByInsuranceId(String insuranceId);

    RecipeDTO getById(int id);

    boolean insert(RecipeDTO acc);

    boolean delete(String insuranceId);

    boolean update(RecipeDTO acc);
}
