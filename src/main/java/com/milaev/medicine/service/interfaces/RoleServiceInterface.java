package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.dto.RoleDTO;

import java.util.List;

public interface RoleServiceInterface {
    List<RoleDTO> getAll();

    RoleDTO getByType(String type);

    RoleDTO getById(Long id);
}
