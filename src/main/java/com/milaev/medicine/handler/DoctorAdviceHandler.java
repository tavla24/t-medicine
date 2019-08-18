package com.milaev.medicine.handler;

import com.milaev.medicine.service.exceptions.DoctorValidationException;
import com.milaev.medicine.service.interfaces.DoctorServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class DoctorAdviceHandler extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(DoctorAdviceHandler.class);

    @ExceptionHandler(DoctorValidationException.class)
    public ModelAndView doctorValidationError(DoctorValidationException ex) {
        log.debug("doctorValidationException executor");
        ModelAndView mav = ex.getModel();
        mav.addObject("org.springframework.validation.BindingResult.dto", ex.getResult());
        mav.addObject("dto", ex.getDTO());
        return PageURLContext.getPage(mav, DoctorServiceInterface.PAGE_REGISTRATION);
    }
}
