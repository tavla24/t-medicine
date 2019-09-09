package com.milaev.medicine.service;

import com.milaev.medicine.dao.PatientDAO;
import com.milaev.medicine.dao.RecipeSimpleDAO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.model.RecipeSimple;
import com.milaev.medicine.exceptions.NullResultFromDBException;
import com.milaev.medicine.exceptions.RecipeSimpleValidationException;
import com.milaev.medicine.utils.PageURLContext;
import com.milaev.medicine.utils.converters.RecipeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Service("recipeSimpleService")
public class RecipeSimpleService extends AbstractService {

    private static Logger log = LoggerFactory.getLogger(RecipeSimpleService.class);

    public static String PAGE_LIST = "recipe/list_simple";
    public static String PAGE_REGISTRATION = "recipe/registration_simple";
    public static String URI_LIST = "/recipe/list";

    @Autowired
    private RecipeSimpleDAO daoRecipeSimple;

    @Autowired
    private PatientDAO daoPatient;

    @Autowired
    private PatientService patientService;

    @Autowired
    private EventService eventService;

    @Transactional
    public ModelAndView mavList(String insuranceId) {
        log.info("called RecipeSimpleService.mavList");
        ModelAndView mav = getPreparedMAV();
        mav.addObject("dto", getByInsuranceId(insuranceId));
        return PageURLContext.getPage(mav, PAGE_LIST);
    }

    @Transactional
    public ModelAndView mavNew(String insuranceId) {
        log.info("called RecipeSimpleService.mavNew");
        ModelAndView mav = getPreparedMAV();
        RecipeSimpleDTO dto = new RecipeSimpleDTO();
        dto.setPatient(patientService.getByInsuranceId(insuranceId));
        mav.addObject("dto", dto);
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Transactional
    public ModelAndView mavNew(RecipeSimpleDTO dto, BindingResult result) {
        log.info("called RecipeSimpleService.mavEdit with dto");
        ModelAndView mav = getPreparedMAV();
        checkDTO(dto, result, mav);
        updateProfile(dto);
        return PageURLContext.getPageRedirect(mav, String.format("%s/%s", URI_LIST, dto.getPatient().getInsuranceId()));
    }

    @Transactional
    public ModelAndView mavDelete(RecipeSimpleDTO dto, String insuranceId) {
        log.info("called RecipeSimpleService.mavDelete");
        dto.getPatient().setInsuranceId(insuranceId);
        delete(dto);
        return PageURLContext.getPageRedirect(new ModelAndView(),String.format("%s/%s", URI_LIST, dto.getPatient().getInsuranceId()));
    }

    @Transactional
    public ModelAndView mavEdit(String insuranceId, Long id) {
        log.info("called RecipeSimpleService.mavEdit");
        ModelAndView mav = getPreparedMAV();
        RecipeSimpleDTO dto = getById(id);
        mav.addObject("dto", dto);
        return PageURLContext.getPage(mav, PAGE_REGISTRATION);
    }

    @Transactional
    public ModelAndView mavEdit(RecipeSimpleDTO dto, BindingResult result) {
        log.info("called RecipeSimpleService.mavEdit with dto");
        return mavNew(dto, result);
    }

    private void checkDTO(RecipeSimpleDTO dto, BindingResult result,
                          ModelAndView mav){
        if (result.hasErrors()) {
            log.info("hasErrors()");
            log.info(result.getAllErrors().toString());
            throw new RecipeSimpleValidationException(dto, result, mav);
        }
    }

    @Transactional
    public List<RecipeSimpleDTO> getAll() {
        List<RecipeSimple> list = daoRecipeSimple.getAll();
        return fillDTO(list);
    }

    @Transactional
    public List<RecipeSimpleDTO> getByInsuranceId(String insuranceId) {
        List<RecipeSimple> list = daoRecipeSimple.getByInsuranceId(insuranceId);
        return fillDTO(list);
    }

    private List<RecipeSimpleDTO> fillDTO(List<RecipeSimple> dbList){
        List<RecipeSimpleDTO> listDTO = new ArrayList<>();
        for (RecipeSimple item : dbList) {
            listDTO.add(RecipeConverter.toDTO(item));
        }
        return listDTO;
    }

    @Transactional
    public RecipeSimpleDTO getById(Long id) {
        return fillDTO(getEntityById(id));
    }

    private RecipeSimple getEntityById(Long id){
        RecipeSimple db = daoRecipeSimple.getById(id);
        if (db == null)
            throw new NullResultFromDBException();
        return db;
    }

    @Transactional
    public void updateProfile(RecipeSimpleDTO dto){
        log.info("service.updateProfile(Recipe) insureId [{}]; id [{}]", dto.getPatient().getInsuranceId(), dto.getId());
        RecipeSimpleDTO rezDTO;
        if (dto.getId() == null)
            rezDTO = insert(dto, new RecipeSimple());
        else
            rezDTO = update(dto, daoRecipeSimple.getById(dto.getId()));

        eventService.updateEvents(rezDTO.getId());
    }

    @Transactional
    public void delete(RecipeSimpleDTO dto) {
        RecipeSimple db = daoRecipeSimple.getById(dto.getId());
        try {
            daoRecipeSimple.delete(db);
        } catch (Exception ex) {
            report(ex);
        }
    }

    private RecipeSimpleDTO insert(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("service.insert(RecipeSimple)");
        fillEntity(dto, db);
        try {
            db = daoRecipeSimple.insert(db);
            return fillDTO(db);
        } catch (Exception ex) {
            report(ex);
        }
        return null;
    }

    private RecipeSimpleDTO update(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("RecipeSimpleService.update(RecipeSimple)");
        fillEntity(dto, db);
        try {
            db = daoRecipeSimple.update(db);
            return fillDTO(db);
        } catch (Exception ex) {
            report(ex);
        }
        return null;
    }

    private void fillEntity(RecipeSimpleDTO dto, RecipeSimple db) {
        log.info("fillEntity");
        Patient a = daoPatient.getByInsuranceId(dto.getPatient().getInsuranceId());
        db.setPatient(a);
        RecipeConverter.toEntity(dto, db);
    }

    private RecipeSimpleDTO fillDTO(RecipeSimple db) {
        log.info("called RecipeSimpleService.fillDTO with db");
        if (db != null) {
            RecipeSimpleDTO dto = RecipeConverter.toDTO(db);
            return dto;
        }
        return null;
    }

    private void report(Exception ex) {
        log.error("Exception from Service during DB query");
        ex.printStackTrace();
        throw new NullResultFromDBException();
    }
}
