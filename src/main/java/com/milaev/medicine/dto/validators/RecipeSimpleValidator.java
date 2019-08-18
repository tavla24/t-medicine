package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.Date;
import java.util.List;

@Component
public class RecipeSimpleValidator  implements Validator {

    private static Logger log = LoggerFactory.getLogger(RecipeSimpleValidator.class);

    @Override
    public boolean supports(Class<?> clazz) {
        return RecipeSimpleDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RecipeSimpleDTO dto = (RecipeSimpleDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healingName", "recipe.healingName.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "healingType", "recipe.healingType.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "doze", "recipe.doze.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateFrom", "recipe.dateFrom.empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTo", "recipe.dateTo.empty");

        if ((dto.getDateFrom() == null) || (dto.getDateTo() == null)) {
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateFrom", "recipe.dateFrom.empty");
//            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateTo", "recipe.dateTo.empty");
        } else {
            if (dto.getDateFrom().after(dto.getDateTo()))
                errors.rejectValue("dateFrom", "recipe.dateFrom.before");
        }

        if (dto.getDayNamesList().size() == 0)
            errors.rejectValue("dayNamesList", "recipe.dayNamesList.empty");

        if (dto.getDayPartsList().size() == 0)
            errors.rejectValue("dayPartsList", "recipe.dayPartsList.empty");
    }
}
