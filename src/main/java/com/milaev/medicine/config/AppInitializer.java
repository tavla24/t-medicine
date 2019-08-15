package com.milaev.medicine.config;

import com.milaev.medicine.config.security.WebSecurityConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    // TODO AbstractAnnotationConfigDispatcherServletInitializer vs
    // AnnotationConfigWebApplicationContext (WebApplicationInitializer)

    // TODO in all notes HibernateConfig.class, SecurityConfig.class - in the
    // getRootConfigClasses()
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

    @Override
    public void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }
}
