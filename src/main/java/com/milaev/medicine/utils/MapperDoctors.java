package com.milaev.medicine.utils;

import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;

@Component
public class MapperDoctors {

    private static ModelMapper mapperToDTO;
    private static ModelMapper mapperToEntity;

    public static BiConsumer<DoctorDTO, Doctor> toEntity() {
        return mapperToEntity::map;
    }

    public static BiConsumer<Doctor, DoctorDTO> toDTO() {
        return mapperToDTO::map;
    }

    @PostConstruct
    public void postConstruct() {
        mapperToEntity = new ModelMapper();
        mapperToEntity.createTypeMap(DoctorDTO.class, Doctor.class).setPropertyCondition(Conditions.isNotNull());
        // .addMappings(map -> map.skip(Account::setId));

        mapperToDTO = new ModelMapper();
        mapperToDTO.createTypeMap(Doctor.class, DoctorDTO.class).setPropertyCondition(Conditions.isNotNull());
        // .addMappings(map -> map.skip(Account::setId));
    }
}
