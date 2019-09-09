package com.milaev.medicine.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class RecipeSimpleValidationException extends DTOValidationException {
    public RecipeSimpleValidationException() {
    }

    public RecipeSimpleValidationException(Object dto, BindingResult result) {
        super(dto, result);
    }

    public RecipeSimpleValidationException(Object dto, BindingResult result, String loggedinuser) {
        super(dto, result, loggedinuser);
    }

    public RecipeSimpleValidationException(Object dto, BindingResult result, ModelAndView model) {
        super(dto, result, model);
    }

    public RecipeSimpleValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        super(dto, result, model, loggedinuser);
    }
}
