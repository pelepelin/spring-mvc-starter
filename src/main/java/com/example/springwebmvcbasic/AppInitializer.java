package com.example.springwebmvcbasic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * @see org.springframework.web.WebApplicationInitializer
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { AppConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/*" };
    }
}
