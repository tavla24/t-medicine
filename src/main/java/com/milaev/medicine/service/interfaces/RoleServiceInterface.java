package com.milaev.medicine.service.interfaces;

import java.util.List;

import com.milaev.medicine.model.Role;

public interface RoleServiceInterface {
    List<Role> getAll();

    Role getByType(String type);

    Role getById(Long id);
}
