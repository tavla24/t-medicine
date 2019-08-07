package com.milaev.medicine.utils;

import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import com.milaev.medicine.dto.*;
import com.milaev.medicine.model.*;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MapperUtil {

    private static ModelMapper mapper;

    public static BiConsumer<PatientDTO, Patient> toEntityPatient() {
        return mapper::map;
    }
    public static BiConsumer<Patient, PatientDTO> toDTOPatient() {
        return mapper::map;
    }

    public static BiConsumer<DoctorDTO, Doctor> toEntityDoctor() {
        return mapper::map;
    }
    public static BiConsumer<Doctor, DoctorDTO> toDTODoctor() {
        return mapper::map;
    }

    public static BiConsumer<AccountDTO, Account> toEntityAccount() {
        return mapper::map;
    }
    public static BiConsumer<Account, AccountDTO> toDTOAccount() {
        return mapper::map;
    }

    public static BiConsumer<RecipeDTO, Recipe> toEntityRecipe() {
        return mapper::map;
    }
    public static BiConsumer<Recipe, RecipeDTO> toDTORecipe() {
        return mapper::map;
    }

    public static BiConsumer<RecipeSimpleDTO, RecipeSimple> toEntityRecipeSimple() {
        return mapper::map;
    }
    public static BiConsumer<RecipeSimple, RecipeSimpleDTO> toDTORecipeSimple() {
        return mapper::map;
    }
    
    public static BiConsumer<DayNameDTO, DayName> toEntityDayName() {
        return mapper::map;
    }
    public static BiConsumer<DayName, DayNameDTO> toDTODayName() {
        return mapper::map;
    }
    
    public static BiConsumer<DayPartDTO, DayPart> toEntityDayPart() {
        return mapper::map;
    }
    public static BiConsumer<DayPart, DayPartDTO> toDTODayPart() {
        return mapper::map;
    }
    
    public static BiConsumer<EventDTO, Event> toEntityEvent() {
        return mapper::map;
    }
    public static BiConsumer<Event, EventDTO> toDTOEvent() {
        return mapper::map;
    }
    
    public static BiConsumer<HealingDTO, Healing> toEntityHealing() {
        return mapper::map;
    }
    public static BiConsumer<Healing, HealingDTO> toDTOHealing() {
        return mapper::map;
    }

    @PostConstruct
    public void postConstruct() {
        mapper = new ModelMapper();

        mapper.createTypeMap(AccountDTO.class, Account.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Account::setId));
        mapper.createTypeMap(Account.class, AccountDTO.class).setPropertyCondition(Conditions.isNotNull());
        // .addMappings(map -> map.skip(AccountDTO::setId));

        mapper.createTypeMap(DoctorDTO.class, Doctor.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Doctor::setId));
        mapper.createTypeMap(Doctor.class, DoctorDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(PatientDTO.class, Patient.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Patient::setId));
        mapper.createTypeMap(Patient.class, PatientDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(RecipeDTO.class, Recipe.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Recipe::setId));
        mapper.createTypeMap(Recipe.class, RecipeDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(RecipeSimpleDTO.class, RecipeSimple.class).setPropertyCondition(Conditions.isNotNull());
                //.addMappings(map -> map.skip(RecipeSimple::setId));
        mapper.createTypeMap(RecipeSimple.class, RecipeSimpleDTO.class).setPropertyCondition(Conditions.isNotNull());
        
        mapper.createTypeMap(DayNameDTO.class, DayName.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(DayName::setId));
        mapper.createTypeMap(DayName.class, DayNameDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(DayPartDTO.class, DayPart.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(DayPart::setId));
        mapper.createTypeMap(DayPart.class, DayPartDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(EventDTO.class, Event.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Event::setId));
        mapper.createTypeMap(Event.class, EventDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(HealingDTO.class, Healing.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Healing::setId));
        mapper.createTypeMap(Healing.class, HealingDTO.class).setPropertyCondition(Conditions.isNotNull());

    }
}
