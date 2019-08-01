package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Role;

public interface RoleDAOInterface {
    List<Role> getAll();

    Role getByType(String type);

    Role getById(int id);
}
