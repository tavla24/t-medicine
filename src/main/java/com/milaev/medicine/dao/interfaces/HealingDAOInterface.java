package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Healing;

public interface HealingDAOInterface {
    
    List<Healing> getByRecipeId(Long id);

    boolean insert(Healing acc);

    boolean delete(Healing acc);

    boolean update(Healing acc);
}
