package com.milaev.medicine.handler;

import com.milaev.medicine.service.exceptions.NullResultFromDBException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.ServletException;

@ControllerAdvice
public class ZlobalAdviceController extends DefaultHandlerExceptionResolver {

    private static Logger log = LoggerFactory.getLogger(ZlobalAdviceController.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle(Exception ex) {
        log.debug("PAGE NOT FOUND. HTTP 404 returned.");
        return fillPageContentDefault(ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServletException.class)
    public ModelAndView notFoundHandler(Exception ex){
            //, HttpServletRequest request, HttpServletResponse response) {
        log.debug("INTERNAL SERVER ERROR. HTTP 500 returned.");
        return fillPageContentDefault(ex);
    }

    @ExceptionHandler(NullResultFromDBException.class)
    public ModelAndView nullResultFromDB(Exception ex) {
        log.debug("Uncorrect request to database.");
        return fillPageContentDefault(ex);
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView myError(Exception ex) {
        log.debug("Global exception occurred.");
        return fillPageContentDefault(ex);
    }

    private ModelAndView fillPageContentDefault(Exception ex){
        return fillPageContent("Something went wrong...", ex);
    }

    private ModelAndView fillPageContent(String message, Exception ex){
        ex.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", ex);
        mav.addObject("errorMessage", message);
        mav.setViewName("error/error");
        return mav;
    }
}
