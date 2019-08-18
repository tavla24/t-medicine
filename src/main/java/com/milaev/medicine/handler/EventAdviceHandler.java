package com.milaev.medicine.handler;

import com.milaev.medicine.service.exceptions.EventValidationException;
import com.milaev.medicine.service.interfaces.EventServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class EventAdviceHandler extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(EventAdviceHandler.class);

    @ExceptionHandler(EventValidationException.class)
    public ModelAndView eventValidationError(EventValidationException ex) {
        log.debug("accountValidationError executor");
        ModelAndView mav = ex.getModel();
        mav.addObject("org.springframework.validation.BindingResult.dto", ex.getResult());
        mav.addObject("dto", ex.getDTO());
        return PageURLContext.getPage(mav, EventServiceInterface.PAGE_REGISTRATION);
    }
}
