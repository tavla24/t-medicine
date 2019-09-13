package com.milaev.medicine.service;

import com.milaev.medicine.config.HibernateConfig;
import com.milaev.medicine.config.WebMvcConfig;
import com.milaev.medicine.config.security.WebSecurityConfig;
import com.milaev.medicine.dto.RecipeSimpleDTO;
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
public class RecipeSimpleServiceTest {

    @Autowired
    RecipeSimpleService recipeService;

    @Test
    public void testGetListAll() {
        recipeService.getAll();
    }

    @Test
    public void testGetById() {
        recipeService.getById(1l);
    }

    @Test
    public void testGetRecipeSimpleDTOwithPatient() {
        recipeService.getRecipeSimpleDTOwithPatient("FGR1458762");
    }

    @Test
    public void testGetByInsuranceId() {
        recipeService.getByInsuranceId("FGR1458762");
    }

    @Test
    public void testUpdateDelete() {
        RecipeSimpleDTO recipe = recipeService.getById(1l);
        recipeService.updateProfile(recipe);
        recipeService.delete(recipe);
        recipe.setId(null);
        recipeService.updateProfile(recipe);
    }
}
