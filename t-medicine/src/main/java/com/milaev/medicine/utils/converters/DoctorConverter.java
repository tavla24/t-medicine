package com.milaev.medicine.utils.converters;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.utils.MapperUtil;

public class DoctorConverter {
    public static DoctorDTO toDTO(Doctor db){
        DoctorDTO dto = new DoctorDTO();
        MapperUtil.toDTODoctor().accept(db, dto);
        dto.setLogin(db.getAccount().getLogin());
        return dto;
    }

    public static Doctor toEntity(DoctorDTO dto, Doctor db){
        MapperUtil.toEntityDoctor().accept(dto, db);
        return db;
    }
}
