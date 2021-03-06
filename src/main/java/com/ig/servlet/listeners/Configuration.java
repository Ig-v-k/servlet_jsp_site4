package com.ig.servlet.listeners;

import com.ig.servlet.filters.AuthenticationFilter;
import com.ig.servlet.filters.LoggingFilter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.EnumSet;

@WebListener()
public class Configuration implements ServletContextListener{
    private static final Logger log = LogManager.getLogger();

    // Public constructor is required by servlet spec
    public Configuration() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
      log.info("log:: contextInitialized()");
      ServletContext context = sce.getServletContext();
      FilterRegistration.Dynamic registration = context.addFilter("loggingFilter", new LoggingFilter());
      registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST, DispatcherType.INCLUDE, DispatcherType.FORWARD, DispatcherType.ERROR), false, "/*");
      registration = context.addFilter("authenticationFilter", new AuthenticationFilter());
      registration.addMappingForUrlPatterns(null, false, "/login", "/courses", "/chat", "/sessions", "/student", "/employee", "/manager");
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}
