package com.milaev.medicine.handler;

import com.milaev.medicine.service.exceptions.PatientValidationException;
import com.milaev.medicine.service.interfaces.PatientServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class PatientAdviceHandler extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(PatientAdviceHandler.class);

    @ExceptionHandler(PatientValidationException.class)
    public ModelAndView patientValidationException(PatientValidationException ex) {
        log.debug("patientValidationException executor");
        ModelAndView mav = ex.getModel();
        mav.addObject("org.springframework.validation.BindingResult.dto", ex.getResult());
        mav.addObject("dto", ex.getDTO());
        return PageURLContext.getPage(mav, PatientServiceInterface.PAGE_REGISTRATION);
    }
}
