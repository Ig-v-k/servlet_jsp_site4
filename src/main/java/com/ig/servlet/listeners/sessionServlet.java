package com.ig.servlet.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener
public class sessionServlet implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener, HttpSessionIdListener {

    private SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyyy HH:mm::ss");

    private String date() {
        return this.formatter.format(new Date());
    }

    // Public constructor is required by servlet spec
    public sessionServlet() {

    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
        System.out.println(this.date() + ": Session " + se.getSession().getId() + " created.");
        SessionRegistry.addSession(se.getSession());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        System.out.println(this.date() + ": Session " + se.getSession().getId() + " created.");
        SessionRegistry.removeSession(se.getSession());
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }

    @Override
    public void sessionIdChanged(HttpSessionEvent e, String s) {
        System.out.println(this.date() + ": Session ID " + s + " changed to " + e.getSession().getId());
        SessionRegistry.updateSessionId(e.getSession(), s);
    }
}
