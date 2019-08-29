package com.milaev.medicine.service;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

public abstract class AbstractService {

    private static Logger log = LoggerFactory.getLogger(AbstractService.class);

    public static String URI_MAIN = "/";

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    public ModelAndView getPreparedMAV() {
        log.info("called AbstractService.getPreparedMAV");
        String loggedinuser = sessionAuth.getUserName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("loggedinuser", loggedinuser);
        return mav;
    }

}
