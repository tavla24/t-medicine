package com.milaev.medicine.config.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import java.io.IOException;

@ControllerAdvice
public class GlobalControllerAdvice extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    MessageSource messageSource;

    // TODO realize

    @ExceptionHandler(IOException.class)
    public ModelAndView myError(Exception exception) {
        log.debug("Exception occurred");
        exception.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("errorMessage", "Something went wrong...");
        mav.setViewName("error/error");
        return mav;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ModelAndView notFoundHandler(Exception exception) {
        log.debug("Item not found. HTTP 500 returned.");
        exception.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("errorMessage", "Something went wrong...");
        mav.setViewName("error/error");
        return mav;
    }
}
