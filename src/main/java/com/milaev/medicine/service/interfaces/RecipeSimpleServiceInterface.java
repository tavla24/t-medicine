package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RecipeSimpleDTO;

import java.util.List;

public interface RecipeSimpleServiceInterface {

    List<RecipeSimpleDTO> getAll();

    List<RecipeSimpleDTO> getByInsuranceId(String insuranceId);

    RecipeSimpleDTO getById(Long id);

    void updateProfile(RecipeSimpleDTO dto);

    void delete(RecipeSimpleDTO dto);

//    void insert(RecipeSimpleDTO acc);
//
//    void update(RecipeSimpleDTO acc);
}
