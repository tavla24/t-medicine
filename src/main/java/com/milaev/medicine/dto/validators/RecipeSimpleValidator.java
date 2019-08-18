package com.milaev.medicine.dto.validators;

import com.milaev.medicine.dto.RecipeSimpleDTO;
import com.milaev.medicine.utils.datetime.DaysOfWeekContainer;
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

        if ((dto.getDateFrom() != null) && (dto.getDateTo() != null)) {
            if (dto.getDateFrom().after(dto.getDateTo()))
                errors.rejectValue("dateFrom", "recipe.dateFrom.before");

                DaysOfWeekContainer dowc = new DaysOfWeekContainer();
                dowc.fill(dto.getDateFrom(), dto.getDateTo(), dto.getDayOfWeekList(), dto.getPartOfDayList());
                if (dowc.getDateTimeCount() == 0)
                    errors.rejectValue("dateFrom", "recipe.dateFrom.dayweaks");

        }

        if (dto.getDayNamesList().size() == 0)
            errors.rejectValue("dayNamesList", "recipe.dayNamesList.empty");

        if (dto.getDayPartsList().size() == 0)
            errors.rejectValue("dayPartsList", "recipe.dayPartsList.empty");
    }
}
