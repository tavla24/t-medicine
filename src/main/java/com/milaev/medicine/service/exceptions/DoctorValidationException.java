package com.milaev.medicine.service.exceptions;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class DoctorValidationException extends DTOValidationException {
    public DoctorValidationException() {
    }

    public DoctorValidationException(Object dto, BindingResult result) {
        super(dto, result);
    }

    public DoctorValidationException(Object dto, BindingResult result, String loggedinuser) {
        super(dto, result, loggedinuser);
    }

    public DoctorValidationException(Object dto, BindingResult result, ModelAndView model) {
        super(dto, result, model);
    }

    public DoctorValidationException(Object dto, BindingResult result, ModelAndView model, String loggedinuser) {
        super(dto, result, model, loggedinuser);
    }
}
