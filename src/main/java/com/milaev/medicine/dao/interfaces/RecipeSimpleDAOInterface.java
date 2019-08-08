package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.RecipeSimple;

import java.util.List;

public interface RecipeSimpleDAOInterface {
    List<RecipeSimple> getAll();

    List<RecipeSimple> getByInsuranceId(String insuranceId);

    void insert(RecipeSimple acc);

    void delete(RecipeSimple acc);

    void update(RecipeSimple acc);
}
