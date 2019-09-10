package com.milaev.medicine.controller;


import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.HibernateTestConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateTestConfig.class
})
@WebAppConfiguration
public class SecurityControllerTest {

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .defaultRequest(get("/").with(user("admin").roles("ADMIN")))
//                .addFilters(springSecurityFilterChain)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void requestAdmin() throws Exception {
        mvc
                .perform(get("/admin/doctor/list").with(user("admin").roles("ADMIN")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withRoles("ADMIN"));
    }

    @Test
    public void requestDoctor() throws Exception {
        mvc
                .perform(get("/doctor/edit").with(user("admin").roles("DOCTOR")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(authenticated().withRoles("DOCTOR"));
    }

    @Test
    public void requestAdminWithWrongRole() throws Exception {
        mvc
                .perform(get("/admin/doctor/list").with(user("admin").roles("DOCTOR")))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(authenticated().withRoles("DOCTOR"));

    }

}
