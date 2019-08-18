package com.milaev.medicine.service;

import com.milaev.medicine.bean.interfaces.SessionAuthenticationInterface;
import com.milaev.medicine.service.interfaces.AbstractServiceInterface;
import com.milaev.medicine.utils.PageURLContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public abstract class AbstractService {

    private static Logger log = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    private SessionAuthenticationInterface sessionAuth;

    protected ModelAndView getPreparedMAV() {
        log.info("called AbstractService.getPreparedMAV");
        String loggedinuser = sessionAuth.getUserName();
        ModelAndView mav = new ModelAndView();
        mav.addObject("loggedinuser", loggedinuser);
        return mav;
    }

}
