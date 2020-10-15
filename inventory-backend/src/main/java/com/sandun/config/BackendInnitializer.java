package com.sandun.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class BackendInnitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws ServletException {

        AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
        annotationConfigWebApplicationContext.register(BackendAppConfig.class);

        DispatcherServlet dispatcherServlet= new DispatcherServlet(annotationConfigWebApplicationContext);

        ServletRegistration.Dynamic customDispatcherServlet = servletContext.addServlet("dispatcherServlet",dispatcherServlet);

        customDispatcherServlet.setLoadOnStartup(1);
        customDispatcherServlet.addMapping("/api/*");


    }
}
