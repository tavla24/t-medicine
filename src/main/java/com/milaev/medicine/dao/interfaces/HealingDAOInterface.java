package com.milaev.medicine.dao.interfaces;

import java.util.List;

import com.milaev.medicine.model.Healing;

public interface HealingDAOInterface {
    
    List<Healing> getByRecipeId(Long id);

    void insert(Healing acc);

    void delete(Healing acc);

    void update(Healing acc);
}
