package com.milaev.medicine.service;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import com.milaev.medicine.dto.PatientDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class
})
@WebAppConfiguration
public class PatientServiceTest {

    @Autowired
    PatientService patientService;

    @Test
    public void testGetListAll() {
        List<PatientDTO> list = patientService.getAll();
//        Assert.assertEquals(list.size(), 2);
    }

    @Test
    public void testGetById() {
        PatientDTO dto = patientService.getById(3l);
        Assert.assertEquals(dto.getInsuranceId(), "FGR1458762");
    }
    @Test
    public void testGetByLogin() {
        PatientDTO dto = patientService.getByLogin("ivanov");
    }
    @Test
    public void testGetByAccount() {
        List<PatientDTO> list = patientService.getByAccount("ivanov");
    }
    @Test
    public void testGetByDoctor() {
        List<PatientDTO> list = patientService.getByDoctor("ivanov");
        Assert.assertEquals(list.size(), 2);
    }
    @Test
    public void testGetPatientDTOwithDoctor() {
        PatientDTO dto = patientService.getPatientDTOwithDoctor("ivanov");
    }
    @Test
    public void testGetByInsuranceId() {
        PatientDTO dto = patientService.getByInsuranceId("HFT4245876");
        patientService.deleteProfile("HFT4245876");
        patientService.updateProfile(dto);
        dto.setInsuranceId("XXX1111111");
        patientService.updateProfile(dto);
    }
}
