package com.milaev.medicine.service;

import com.milaev.medicine.dao.DayNameDAO;
import com.milaev.medicine.dao.DayPartDAO;
import com.milaev.medicine.dao.RecipeDAO;
import com.milaev.medicine.dto.DayNameDTO;
import com.milaev.medicine.dto.DayPartDTO;
import com.milaev.medicine.dto.RecipeDTO;
import com.milaev.medicine.model.DayName;
import com.milaev.medicine.model.DayPart;
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

    @Autowired
    private DayNameDAO daoDayName;

    @Autowired
    private DayPartDAO daoDayPart;

    @Override
    @Transactional
    public List<RecipeDTO> getAll() {
        List<Recipe> list = daoRecipe.getAll();
        List<RecipeDTO> listDTO = new ArrayList<>();
        for (Recipe item : list) {
            RecipeDTO dto = new RecipeDTO();
            MapperUtil.toDTORecipe().accept(item, dto);

            listDTO.add(fillRecipeDTO(dto));
        }
        return listDTO;
    }

    private RecipeDTO fillRecipeDTO(RecipeDTO dto){
        List<DayName> listDN = daoDayName.getByRecipeId(dto.getId());
        List<DayNameDTO> listDNDTO = new ArrayList<>();
        for (DayName itemDN : listDN) {
            DayNameDTO dtoDN = new DayNameDTO();
            MapperUtil.toDTODayName().accept(itemDN, dtoDN);

            List<DayPart> listDP = daoDayPart.getByDayNameId(dtoDN.getId());
            List<DayPartDTO> listDPDTO = new ArrayList<>();
            for (DayPart itemDP : listDP) {
                DayPartDTO dtoDP = new DayPartDTO();
                MapperUtil.toDTODayPart().accept(itemDP, dtoDP);
                listDPDTO.add(dtoDP);
            }
            dtoDN.setDayParts(listDPDTO);

            listDNDTO.add(dtoDN);
        }
        dto.setDayNames(listDNDTO);
        return dto;
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
        return fillRecipeDTO(dto);
    }

    @Override
    @Transactional
    public RecipeDTO getById(Long id) {
        Recipe db = daoRecipe.getById(id);
        RecipeDTO dto = new RecipeDTO();
        if (db != null)
            MapperUtil.toDTORecipe().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public void insert(RecipeDTO acc) {
        // TODO
    }

    @Override
    @Transactional
    public void delete(String insuranceId) {
        Recipe db = daoRecipe.getByInsuranceId(insuranceId);
        try {
            daoRecipe.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void update(RecipeDTO acc) {
        // TODO
    }
}
