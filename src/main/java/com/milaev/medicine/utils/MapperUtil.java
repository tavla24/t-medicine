package com.milaev.medicine.utils;

import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.dto.PatientDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Patient;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;

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

    @PostConstruct
    public void postConstruct() {
        mapper = new ModelMapper();

        mapper.createTypeMap(AccountDTO.class, Account.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Account::setId));
        mapper.createTypeMap(Account.class, AccountDTO.class).setPropertyCondition(Conditions.isNotNull());
        //.addMappings(map -> map.skip(AccountDTO::setId));

        mapper.createTypeMap(DoctorDTO.class, Doctor.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Doctor::setId));
        mapper.createTypeMap(Doctor.class, DoctorDTO.class).setPropertyCondition(Conditions.isNotNull());

        mapper.createTypeMap(PatientDTO.class, Patient.class).setPropertyCondition(Conditions.isNotNull())
                .addMappings(map -> map.skip(Patient::setId));
        mapper.createTypeMap(Patient.class, PatientDTO.class).setPropertyCondition(Conditions.isNotNull());
    }
}
