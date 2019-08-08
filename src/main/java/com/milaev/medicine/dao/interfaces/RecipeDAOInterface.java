package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeDAOInterface {
    List<Recipe> getAll();

    Recipe getByInsuranceId(String insuranceId);

    void insert(Recipe acc);

    void delete(Recipe acc);

    void update(Recipe acc);
}
