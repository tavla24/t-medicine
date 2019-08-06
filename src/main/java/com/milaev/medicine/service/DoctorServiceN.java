package com.milaev.medicine.service;

import com.milaev.medicine.dao.AccountDAO;
import com.milaev.medicine.dao.DoctorDAO;
import com.milaev.medicine.dao.interfaces.PersonDAOInterface;
import com.milaev.medicine.dto.DoctorDTO;
import com.milaev.medicine.dto.ViewDoctorDTO;
import com.milaev.medicine.model.Account;
import com.milaev.medicine.model.Doctor;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.MapperUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public PersonDAOInterface<Doctor> getDAO() {
        return daoDoctor;
    }

    @Override
    @Transactional
    public void fillDTODataToEntity(DoctorDTO dto, Doctor entity) {
    }

    @Override
    public void updateProfile(ViewDoctorDTO dto) {
    }

    @Override
    public void updateProfile(ViewDoctorDTO dto, String login) {
    }
}
