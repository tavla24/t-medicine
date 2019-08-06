package com.milaev.medicine.converter;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.utils.MapperUtil;

public class DoctorConv {
    public static DoctorDTO toDTO(Doctor db){
        DoctorDTO dto = new DoctorDTO();
        MapperUtil.toDTODoctor().accept(db, dto);
        dto.setLogin(db.getAccount().getLogin());
        return dto;
    }

    public static Doctor toEntity(DoctorDTO dto){
        Doctor db = new Doctor();
        MapperUtil.toEntityDoctor().accept(dto, db);
        return db;
    }
}
