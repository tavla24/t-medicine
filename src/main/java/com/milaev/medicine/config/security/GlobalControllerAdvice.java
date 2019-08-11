package com.milaev.medicine.config.security;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class GlobalControllerAdvice extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(GlobalControllerAdvice.class);

    @Autowired
    MessageSource messageSource;

    // TODO realize

    @ExceptionHandler(IOException.class)
    public ModelAndView myError(Exception exception) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("errorMessage", "IOException occurred");
        mav.setViewName("error/error");
        return mav;
    }
}
