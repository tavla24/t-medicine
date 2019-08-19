package com.milaev.medicine.controller;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.Filter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class
})
@WebAppConfiguration
public class AccountsControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult result = mvc.perform(get("/admin/account/list")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/account/list.jsp"))
                .andReturn();

        ModelAndView mav = result.getModelAndView();
    }

    @Test
    public void testGetNew() throws Exception {
        MvcResult result = mvc.perform(get("/admin/account/new")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/account/registration.jsp"))
                .andReturn();

        ModelAndView mav = result.getModelAndView();
    }

}
