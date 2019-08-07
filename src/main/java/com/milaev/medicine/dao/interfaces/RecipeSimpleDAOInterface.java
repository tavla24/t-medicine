package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.RecipeSimple;

import java.util.List;

public interface RecipeSimpleDAOInterface {
    List<RecipeSimple> getAll();

    RecipeSimple getByInsuranceId(String insuranceId);

    boolean insert(RecipeSimple acc);

    boolean delete(RecipeSimple acc);

    boolean update(RecipeSimple acc);
}
