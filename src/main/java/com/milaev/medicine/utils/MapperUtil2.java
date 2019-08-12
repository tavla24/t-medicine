package com.milaev.medicine.utils;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;

import java.lang.reflect.ParameterizedType;
import java.util.function.BiConsumer;

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
