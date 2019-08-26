package com.milaev.medicine.service.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class AccountValidationException extends DTOValidationException {
    public AccountValidationException() {
    }

    public AccountValidationException(Object dto, BindingResult result) {
        super(dto, result);
    }

    public AccountValidationException(Object dto, BindingResult result, String loggedinuser) {
        super(dto, result, loggedinuser);
    }

    public AccountValidationException(Object dto, BindingResult result, ModelAndView model) {
        super(dto, result, model);
    }

    public AccountValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        super(dto, result, model, loggedinuser);
    }
}
