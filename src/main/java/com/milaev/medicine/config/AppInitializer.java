package com.milaev.medicine.config;

import javax.servlet.Filter;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.milaev.medicine.config.security.WebSecurityConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // TODO AbstractAnnotationConfigDispatcherServletInitializer vs
    // AnnotationConfigWebApplicationContext (WebApplicationInitializer)
    
    // TODO in all notes HibernateConfig.class, SecurityConfig.class - in the getRootConfigClasses()
    // WebMvcConfig.class - in the getServletConfigClasses()
    @Override
    protected Class<?>[] getRootConfigClasses() {
        // return new Class[] { HibernateConfig.class };
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class, WebSecurityConfig.class, HibernateConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[] { characterEncodingFilter };
    }
}
