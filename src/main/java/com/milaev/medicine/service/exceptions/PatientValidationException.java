package com.milaev.medicine.service.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class PatientValidationException extends DTOValidationException {
    public PatientValidationException() {
    }

    public PatientValidationException(Object dto, BindingResult result) {
        super(dto, result);
    }

    public PatientValidationException(Object dto, BindingResult result, String loggedinuser) {
        super(dto, result, loggedinuser);
    }

    public PatientValidationException(Object dto, BindingResult result, ModelAndView model) {
        super(dto, result, model);
    }

    public PatientValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        super(dto, result, model, loggedinuser);
    }
}
