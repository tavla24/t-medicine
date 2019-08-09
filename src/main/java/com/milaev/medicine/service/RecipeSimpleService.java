package com.milaev.medicine.service;

import com.milaev.medicine.dao.PatientDAO;
import com.milaev.medicine.dao.RecipeSimpleDAO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
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
public class RecipeSimpleService implements RecipeSimpleServiceInterface {

    private static Logger log = LoggerFactory.getLogger(RecipeSimpleService.class);

    @Autowired
    private RecipeSimpleDAO daoRecipeSimple;

    @Autowired
    private PatientDAO daoPatient;

    @Autowired
    private EventServiceInterface eventService;

    @Override
    @Transactional
    public List<RecipeSimpleDTO> getAll() {
        List<RecipeSimple> list = daoRecipeSimple.getAll();
        return fillDTO(list);
    }

    @Override
    @Transactional
    public List<RecipeSimpleDTO> getByInsuranceId(String insuranceId) {
        List<RecipeSimple> list = daoRecipeSimple.getByInsuranceId(insuranceId);
        return fillDTO(list);
    }

    private List<RecipeSimpleDTO> fillDTO(List<RecipeSimple> dbList){
        List<RecipeSimpleDTO> listDTO = new ArrayList<>();
        for (RecipeSimple item : dbList) {
            RecipeSimpleDTO dto = new RecipeSimpleDTO();
            MapperUtil.toDTORecipeSimple().accept(item, dto);
            listDTO.add(dto);
        }
        return listDTO;
    }

    @Override
    @Transactional
    public RecipeSimpleDTO getById(Long id) {
        RecipeSimple db = daoRecipeSimple.getById(id);
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        if (db != null)
            MapperUtil.toDTORecipeSimple().accept(db, dto);
        return dto;
    }

    @Override
    @Transactional
    public void updateProfile(RecipeSimpleDTO dto){
        log.info("service.updateProfile(Recipe) insureId [{}]; id [{}]", dto.getPatient().getInsuranceId(), dto.getId());
        dto.convListToDayTypes();
        dto.convListToDayNames();
        //RecipeSimple db = daoRecipeSimple.getById(dto.getId());

        if (dto.getId() == null)
            insert(dto, new RecipeSimple());
        else
            update(dto, daoRecipeSimple.getById(dto.getId()));

        // TODO update events
        eventService.updateEvents(dto.getId());
    }

    @Override
    @Transactional
    public void delete(RecipeSimpleDTO dto) {
        RecipeSimple db = daoRecipeSimple.getById(dto.getId());
        try {
            daoRecipeSimple.delete(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void insert(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("service.insert(RecipeSimple)");
        fillDTODataToEntity(dto, db);
        try {
            daoRecipeSimple.insert(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void update(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("service.update(RecipeSimple)");
        fillDTODataToEntity(dto, db);
        try {
            daoRecipeSimple.update(db);
        } catch (Exception ex) {
            log.error("Exception from Service during DB query");
            ex.printStackTrace();
        }
    }

    private void fillDTODataToEntity(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("fillDTODataToEntity");
        Patient a = daoPatient.getByInsuranceId(dto.getPatient().getInsuranceId());
        db.setPatient(a);
        MapperUtil.toEntityRecipeSimple().accept(dto, db);
    }
}
