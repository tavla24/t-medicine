package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.utils.MapperUtil;

public class PatientConverter {

    public static PatientDTO toDTO(Patient db){
        PatientDTO dto = new PatientDTO();
        MapperUtil.toDTOPatient().accept(db, dto);
        dto.getDoctor().setLogin(db.getDoctor().getAccount().getLogin());
        return dto;
    }
}
