package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dao.interfaces.PersonDAOInterface;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("doctorServiceN")
public class DoctorServiceN extends PersonService<Doctor, DoctorDTO> implements DoctorServiceInterface {

    private static Logger log = LoggerFactory.getLogger(DoctorServiceN.class);

    @Autowired
    private DoctorDAO daoDoctor;
    @Autowired
    private AccountDAO daoAccount;

    public DoctorServiceN(){
        super();
    }

    @Override
    public PersonDAOInterface<Doctor> getDAO() {
        return daoDoctor;
    }

    @Override
    public void fillDTODataToEntity(DoctorDTO dto, Doctor entity) {
        // TODO question - is it normal way?
        log.info("fillDTODataToEntity");
        log.info(dto.toString());
        Account a = daoAccount.getByLogin(dto.getAccount().getLogin());
        log.info("!!! a: {}", a.toString());
        //Role r = daoRole.getByType(a.getRole().getType());
        //log.info("!!! r: {}", r.getType());
        //a.setRole(r);
        //log.info("!!! a: {}", a.toString());
        entity.setAccount(a);
        MapperUtil.toEntityDoctor().accept(dto, entity);
        log.info("!!! entity role: {}", entity.getAccount().getRole().getType());

        // TODO sometime work, sometime not
        entity.setAccount(a);
        log.info("!!! entity role: {}", entity.getAccount().getRole().getType());
    }
}
