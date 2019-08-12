package com.milaev.medicine.service.interfaces;

import com.milaev.medicine.model.Role;

import java.util.List;

public interface RoleServiceInterface {
    List<Role> getAll();

    Role getByType(String type);

    Role getById(Long id);
}
