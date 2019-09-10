package com.milaev.medicine.dto.validators;

import com.milaev.medicine.config.TestConfig;
import com.milaev.medicine.dto.EventDTO;
import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.EventStatus;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class EventValidatorTest {

    @InjectMocks
    EventValidator eventValidator;

    private EventDTO dto;

    private Errors errors;

    private static final String value_str = "string";

    @Before
    public void setup() {
        dto = new EventDTO();
        dto.setDatestamp(new Date());
        dto.setInfo(value_str);
        dto.setStatus(EventStatus.PLAN.name());
        RecipeSimpleDTO recipe = new RecipeSimpleDTO();
        dto.setRecipe(recipe);

        errors = new BeanPropertyBindingResult(dto, "dto");
    }

    @Test
    public void validateEventWithCorrectData() {
        eventValidator.validate(dto, errors);

        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validateEventWithMissingInfo() {
        dto.setInfo("");
        dto.setStatus(EventStatus.CANCEL.name());
        eventValidator.validate(dto, errors);

        Assert.assertNotNull( errors.getFieldError("info") );
    }
}
