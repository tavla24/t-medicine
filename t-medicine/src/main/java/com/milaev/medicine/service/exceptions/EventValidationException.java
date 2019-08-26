package com.milaev.medicine.service.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class EventValidationException extends DTOValidationException {
    public EventValidationException() {
    }

    public EventValidationException(Object dto, BindingResult result) {
        super(dto, result);
    }

    public EventValidationException(Object dto, BindingResult result, String loggedinuser) {
        super(dto, result, loggedinuser);
    }

    public EventValidationException(Object dto, BindingResult result, ModelAndView model) {
        super(dto, result, model);
    }

    public EventValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        super(dto, result, model, loggedinuser);
    }
}
