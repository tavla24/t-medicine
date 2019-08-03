package com.milaev.medicine.utils;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

public class MapperUtilOld<E, D> {
    private final Class<E> classE;
    private final Class<D> classD;

    public MapperUtilOld(Class<E> classE, Class<D> classD) {
        this.classE = classE;
        this.classD = classD;
    }

    public D entityToDTO(E entity) {
        return (new ModelMapper()).map(entity, classD);
    }

    public E dtoToEntity(D dto) {
        return (new ModelMapper()).map(dto, classE);
    }

    // TODO Lists
    public List<D> entityToDTOList(List<E> entitieList) {
        return (new ModelMapper()).map(entitieList, new TypeToken<List<D>>() {
        }.getType());
    }

    public List<E> dtoToEntityList(List<D> dtoList) {
        // return (new ModelMapper()).map(dtoList, classE.getGenericSuperclass());
        // String classn = classE.getName();
        Type listType = classE;
        return (new ModelMapper()).map(dtoList, listType);
    }
}
