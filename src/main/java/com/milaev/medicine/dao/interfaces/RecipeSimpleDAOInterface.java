package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.RecipeSimple;

import java.util.List;

public interface RecipeSimpleDAOInterface {
    List<RecipeSimple> getAll();

    List<RecipeSimple> getByInsuranceId(String insuranceId);

    RecipeSimple insert(RecipeSimple acc);

    RecipeSimple delete(RecipeSimple acc);

    RecipeSimple update(RecipeSimple acc);
}
