package com.milaev.medicine.dao;

import com.milaev.medicine.dao.interfaces.RoleDAOInterface;
import com.milaev.medicine.model.Role;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO extends AbstractDAO<Role> implements RoleDAOInterface {

    private static Logger log = LoggerFactory.getLogger(RoleDAO.class);

    @SuppressWarnings("unchecked")
    @Override
    public List<Role> getAll() {
        Query<Role> query = getCurrentSession().createQuery("from Role");
        return getAll(query);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Role getByType(String type) {
        Query<Role> query = getCurrentSession().createQuery("from Role where type = :param1");
        return getByParamsSingle(query, type);
    }

}
