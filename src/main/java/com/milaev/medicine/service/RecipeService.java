package com.milaev.medicine.service;

import com.milaev.medicine.dao.RecipeDAO;
import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.model.Recipe;
import com.milaev.medicine.service.interfaces.RecipeServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("recipeService")
public class RecipeService implements RecipeServiceInterface {

    private static Logger log = LoggerFactory.getLogger(RecipeService.class);

    @Autowired
    private RecipeDAO daoRecipe;

    @Override
    @Transactional
    public List<RecipeDTO> getAll() {
        List<Recipe> list = daoRecipe.getAll();
        List<RecipeDTO> listDAO = new ArrayList<>();
        for (Recipe item : list) {
            RecipeDTO dto = new RecipeDTO();
            MapperUtil.toDTORecipe().accept(item, dto);
            listDAO.add(dto);
        }
        return listDAO;
    }

    @Override
    @Transactional
    public RecipeDTO getByInsuranceId(String insuranceId) {
        Recipe db = daoRecipe.getByInsuranceId(insuranceId);
        RecipeDTO dto = new RecipeDTO();
        if (db != null) {
            MapperUtil.toDTORecipe().accept(db, dto);
            MapperUtil.toDTOPatient().accept(db.getPatient(), dto.getPatient());
        }
        return dto;
    }

    @Override
    @Transactional
    public RecipeDTO getById(int id) {
        Recipe db = daoRecipe.getById(id);
        RecipeDTO dto = new RecipeDTO();
        if (db != null)
            MapperUtil.toDTORecipe().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public boolean insert(RecipeDTO acc) {
        // TODO
        return false;
    }

    @Override
    @Transactional
    public boolean delete(String insuranceId) {
        Recipe db = daoRecipe.getByInsuranceId(insuranceId);
        try {
            daoRecipe.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(RecipeDTO acc) {
        // TODO
        return false;
    }
}
