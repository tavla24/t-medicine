package com.milaev.medicine.service;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import com.milaev.medicine.dto.AccountDTO;
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
public class AccountServiceTest {

    @Autowired
    AccountService accountService;

    @Test
    public void testGetListAll() {
        accountService.getAll();
    }

    @Test
    public void testGetById() {
        accountService.getById(0l);
    }

    @Test
    public void testIsLoginUnique() {
        accountService.isLoginUnique("admin");
    }

    @Test
    public void testGetByLogin() {
        AccountDTO acc = accountService.getByLogin("admin");
    }

    @Test
    public void testUpdate() {
        AccountDTO acc = accountService.getByLogin("admin");
        accountService.update(acc, "admin");
    }

    @Test
    public void testDelete() {
        AccountDTO acc = accountService.getByLogin("admin");
        acc.setLogin("tmp");
        accountService.insert(acc);
        accountService.deleteByLogin("tmp");
    }
}
