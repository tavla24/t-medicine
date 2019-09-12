package com.milaev.medicine.service;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import com.milaev.medicine.dto.DoctorDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class
})
@WebAppConfiguration
public class DoctorServiceTest {

    @Autowired
    DoctorService doctorService;

    @Test
    public void testGetListAll(){
        doctorService.getAll();
    }

    @Test
    public void testGetById() {
        DoctorDTO doc = doctorService.getById(new Long(1));
    }

    @Test
    public void testIsProfileExist() {
        doctorService.isProfileExist("ivanov");
    }

    @Test
    public void testGetByLogin() {
        doctorService.getByLogin("ivanov");
    }

    @Test
    public void testProfileExist() {
        doctorService.isProfileExist("ivanov");
    }

    @Test
    public void testUpdate() {
        DoctorDTO doc = doctorService.getById(new Long(1));
        doc.setLogin("defdoc");
        doctorService.updateProfile(doc);
    }

    @Test
    public void testDelete() {
        doctorService.deleteProfile("petrov");
    }
}
