package com.milaev.medicine.dao.interfaces;

import com.milaev.medicine.model.Role;

import java.util.List;

public interface RoleDAOInterface {

    List<Role> getAll();

    Role getByType(String type);

}
