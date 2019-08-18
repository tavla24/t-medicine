package com.milaev.medicine.utils;

import org.springframework.web.servlet.ModelAndView;

public class PageURLContext {

    public static ModelAndView getPage(ModelAndView mav, String url){
        return getPage(mav, url, false);
    }

    public static ModelAndView getPageRedirect(ModelAndView mav, String url){
        return getPage(mav, url, true);
    }

    public static ModelAndView getPage(ModelAndView mav, String url, boolean redirect){
        mav.setViewName(getPage(url, redirect));
        return mav;
    }

    public static String getPage(String url){
        return getPage(url, false);
    }

    public static String getPageRedirect(String url){
        return getPage(url, true);
    }

    public static String getPage(String url, boolean redirect){
        if (redirect)
            url = "redirect:".concat(url);
        return url;
    }
}
