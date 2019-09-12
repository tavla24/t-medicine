package com.milaev.medicine.controller;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class
})
@WebAppConfiguration
public class RecipeControllerTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    public void testGetList() throws Exception {
        MvcResult result = mvc.perform(get("/recipe/list/FGR1458762")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/recipe/list_simple.jsp"))
                .andReturn();
    }

    @Test
    public void testCreateNew() throws Exception {
        MvcResult result = mvc.perform(get("/recipe/list/FGR1458762")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/recipe/list_simple.jsp"))
                .andReturn();
    }

    @Test
    public void testEdit() throws Exception {
        MvcResult result = mvc.perform(get("/recipe/edit/FGR1458762/1")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/pages/recipe/registration_simple.jsp"))
                .andReturn();
    }
}
