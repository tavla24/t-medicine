package com.milaev.medicine.converter;

import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Patient;
import com.milaev.medicine.utils.MapperUtil;

public class PatientConv {

    public static PatientDTO toDTO(Patient db){
        PatientDTO dto = new PatientDTO();
        MapperUtil.toDTOPatient().accept(db, dto);
        dto.getDoctor().setLogin(db.getDoctor().getAccount().getLogin());
        return dto;
    }
}
