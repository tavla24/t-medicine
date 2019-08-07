package com.milaev.medicine.service;

import com.milaev.medicine.dao.DayNameDAO;
import com.milaev.medicine.dao.DayPartDAO;
import com.milaev.medicine.dao.RecipeSimpleDAO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.service.interfaces.RecipeSimpleServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("recipeSimpleService")
public class RecipeSimpleSimpleService implements RecipeSimpleServiceInterface {

    private static Logger log = LoggerFactory.getLogger(RecipeSimpleSimpleService.class);

    @Autowired
    private RecipeSimpleDAO daoRecipeSimple;

    @Autowired
    private DayNameDAO daoDayName;

    @Autowired
    private DayPartDAO daoDayPart;

    @Override
    @Transactional
    public List<RecipeSimpleDTO> getAll() {
        List<RecipeSimple> list = daoRecipeSimple.getAll();
        List<RecipeSimpleDTO> listDTO = new ArrayList<>();
        for (RecipeSimple item : list) {
            RecipeSimpleDTO dto = new RecipeSimpleDTO();
            MapperUtil.toDTORecipeSimple().accept(item, dto);
        }
        return listDTO;
    }

    @Override
    @Transactional
    public RecipeSimpleDTO getByInsuranceId(String insuranceId) {
        RecipeSimple db = daoRecipeSimple.getByInsuranceId(insuranceId);
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        if (db != null) {
            MapperUtil.toDTORecipeSimple().accept(db, dto);
            //MapperUtil.toDTOPatient().accept(db.getPatient(), dto.getPatient());
        }
        return dto;
    }

    @Override
    @Transactional
    public RecipeSimpleDTO getById(int id) {
        RecipeSimple db = daoRecipeSimple.getById(id);
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        if (db != null)
            MapperUtil.toDTORecipeSimple().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public boolean insert(RecipeSimpleDTO acc) {
        // TODO
        return false;
    }

    @Override
    @Transactional
    public boolean delete(String insuranceId) {
        RecipeSimple db = daoRecipeSimple.getByInsuranceId(insuranceId);
        try {
            daoRecipeSimple.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean update(RecipeSimpleDTO acc) {
        // TODO
        return false;
    }
}
