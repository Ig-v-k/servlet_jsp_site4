package com.ig.servlet.filters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



public class AuthenticationFilter implements Filter {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log:: doFilter()");
        HttpSession session = ((HttpServletRequest)request).getSession(false);

/*
        if(session == null || session.getAttribute("username") == null || session.getAttribute("uid") == null)
            ((HttpServletResponse)response).sendRedirect("login");
        else
            chain.doFilter(request, response);
*/
    }

    @Override
    public void init(FilterConfig config) throws ServletException { }

    @Override
    public void destroy() { }
}
