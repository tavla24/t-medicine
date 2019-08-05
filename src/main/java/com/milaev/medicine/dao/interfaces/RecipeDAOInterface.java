package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Recipe;

import java.util.List;

public interface RecipeDAOInterface {
    List<Recipe> getAll();

    Recipe getByInsuranceId(String insuranceId);

    boolean insert(Recipe acc);

    boolean delete(Recipe acc);

    boolean update(Recipe acc);
}
