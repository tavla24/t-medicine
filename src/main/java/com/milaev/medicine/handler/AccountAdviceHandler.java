package com.milaev.medicine.handler;

import com.milaev.medicine.service.exceptions.AccountValidationException;
import com.milaev.medicine.service.interfaces.AccountServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class AccountAdviceHandler extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(AccountAdviceHandler.class);

    @ExceptionHandler(AccountValidationException.class)
    public ModelAndView accountValidationError(AccountValidationException ex) {
        log.debug("accountValidationError executor");
        ModelAndView mav = ex.getModel();
        mav.addObject("org.springframework.validation.BindingResult.account", ex.getResult());
        mav.addObject("account", ex.getDTO());
        return PageURLContext.getPage(mav, AccountServiceInterface.PAGE_REGISTRATION);
    }
}
