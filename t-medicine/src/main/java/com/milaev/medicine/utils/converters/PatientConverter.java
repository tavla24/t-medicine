package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.model.enums.PatientStatus;
import com.milaev.medicine.utils.MapperUtil;

public class PatientConverter {

    public static PatientDTO toDTO(Patient db){
        PatientDTO dto = new PatientDTO();
        MapperUtil.toDTOPatient().accept(db, dto);
        dto.setDoctor(DoctorConverter.toDTO(db.getDoctor()));
        dto.setOldInsuranceId(db.getInsuranceId());
        return dto;
    }

    public static Patient toEntity(PatientDTO dto, Patient db){
        MapperUtil.toEntityPatient().accept(dto, db);
        return db;
    }
}
