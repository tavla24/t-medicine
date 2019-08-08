package com.milaev.medicine.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.milaev.medicine.controller.AdminAccountsController;
import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.service.interfaces.RoleServiceInterface;

@Service
public class RoleService implements RoleServiceInterface {

    private static Logger log = LoggerFactory.getLogger(AdminAccountsController.class);

    @Autowired
    private RoleDAO dao;

    @Override
    @Transactional
    public List<Role> getAll() {
        return dao.getAll();
    }

    @Override
    @Transactional
    public Role getByType(String type) {
        return dao.getByType(type);
    }

    @Override
    @Transactional
    public Role getById(Long id) {
        return dao.getById(id);
    }

}
