package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.model.enums.DayNameTypes;
import com.milaev.medicine.model.enums.DayPartTypes;
import com.milaev.medicine.model.enums.HealingType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

@RunWith(MockitoJUnitRunner.class)
public class RecipeSimpleValidatorTest {

    @InjectMocks
    RecipeSimpleValidator recipeValidator;

    RecipeSimpleDTO dto;

    private static final String value_str = "string";

    private Errors errors;
    private DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @Before
    public void setup() throws ParseException {
        dto = new RecipeSimpleDTO();
        dto.setHealingName(value_str);
        dto.setHealingType(HealingType.DRUG.name());
        dto.setDoze(value_str);
        dto.setDateFrom(format.parse("01/01/2019"));
        dto.setDateTo(format.parse("02/02/2019"));
        dto.setDayNamesList(Arrays.asList(DayNameTypes.MONDAY.name(), DayNameTypes.THURSDAY.name()));
        dto.setDayPartsList(Arrays.asList(DayPartTypes.MORNING.name(), DayPartTypes.EVENING.name()));

        errors = new BeanPropertyBindingResult(dto, "dto");
    }

    @Test
    public void validatePatientDTO() {
        recipeValidator.validate(dto, errors);
        Assert.assertFalse(errors.hasErrors());
    }

    @Test
    public void validatePatientDTOWithWrongDate() throws ParseException {
        dto.setDateTo(format.parse("02/02/2018"));
        recipeValidator.validate(dto, errors);
        Assert.assertNotNull( errors.getFieldError("dateFrom") );
    }

    @Test
    public void validatePatientDTOWithWrongDateParts(){
        dto.setDayPartsList(new ArrayList<>());
        recipeValidator.validate(dto, errors);
        Assert.assertNotNull( errors.getFieldError("dateFrom") );
    }

    @Test
    public void validatePatientDTOWithWrongDateNames(){
        dto.setDayNamesList(new ArrayList<>());
        recipeValidator.validate(dto, errors);
        Assert.assertNotNull( errors.getFieldError("dateFrom") );
    }
}
