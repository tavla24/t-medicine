package com.milaev.medicine.service;

import com.milaev.medicine.dao.RoleDAO;
import com.milaev.medicine.dto.RoleDTO;
import com.milaev.medicine.model.Role;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {

    private static Logger log = LoggerFactory.getLogger(RoleService.class);

    @Autowired
    private RoleDAO daoRole;

    @Transactional
    public List<RoleDTO> getAll() {
        List<Role> list = daoRole.getAll();
        List<RoleDTO> listDAO = new ArrayList<>();
        for (Role item : list) {
            listDAO.add(fillDTO(item));
        }
        return listDAO;
    }

    @Transactional
    public RoleDTO getByType(String type) {
        Role db = daoRole.getByType(type);
        return fillDTO(db);
    }

    @Transactional
    public RoleDTO getById(Long id) {
        Role db = daoRole.getById(id);
        return fillDTO(db);
    }

    private RoleDTO fillDTO(Role db){
        if (db != null) {
            RoleDTO roleDTO = new RoleDTO();
            MapperUtil.toDTORole().accept(db, roleDTO);
            return roleDTO;
        }
        return null;
    }

}
