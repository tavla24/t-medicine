package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeSimpleServiceInterface {

    List<RecipeSimpleDTO> getAll();

    RecipeSimpleDTO getByInsuranceId(String insuranceId);

    RecipeSimpleDTO getById(int id);

    boolean insert(RecipeSimpleDTO acc);

    boolean delete(String insuranceId);

    boolean update(RecipeSimpleDTO acc);
}
