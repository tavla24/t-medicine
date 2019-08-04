package com.milaev.medicine.utils;

import java.lang.reflect.ParameterizedType;
import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;

import com.milaev.medicine.dto.AccountDTO;
import com.milaev.medicine.model.Account;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Doctor;

//@Component
public class MapperUtil2<E, D> {

    private ModelMapper mapper;

    private final Class<E> classE;
    private final Class<D> classD;

    public MapperUtil2() {
        this.classE = (Class<E>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        this.classD = (Class<D>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];

        mapper = new ModelMapper();

        mapper.createTypeMap(classD, classE).setPropertyCondition(Conditions.isNotNull());
        mapper.createTypeMap(classE, classD).setPropertyCondition(Conditions.isNotNull());
    }

    public BiConsumer<E, D> toDTO() {
        return mapper::map;
    }

    public BiConsumer<D, E> toEntity() {
        return mapper::map;
    }

//    @PostConstruct
//    public void postConstruct() {
//        mapper = new ModelMapper();
//
//        mapper.createTypeMap(classD, classE).setPropertyCondition(Conditions.isNotNull());
//        mapper.createTypeMap(classE, classD).setPropertyCondition(Conditions.isNotNull());
//    }
}
