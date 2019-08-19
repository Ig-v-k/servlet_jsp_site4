package com.ig.servlet.filters;

import com.ig.model.UserAccount;
import com.ig.servlet.AppUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class LoggingFilter implements Filter {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log:: doFilter()");
        boolean clear = false;
        log.info("log:: ThreadContext.containsKey(\"id\")" + ThreadContext.containsKey("id"));
        if(!ThreadContext.containsKey("id")) {
            clear = true;
            ThreadContext.put("id", UUID.randomUUID().toString());
            HttpSession session = ((HttpServletRequest)request).getSession();
            if(session != null) {
                log.info("log:: ThreadContext.put()");
                log.info("log:: username ---> " + session.getAttribute("username"));
                ThreadContext.put("username", (String) session.getAttribute("username"));
            }
        }
        try {
            log.info("log:: try - chain.doFilter()");
            chain.doFilter(request, response);
        }
        finally {
            if(clear) {
                log.info("log:: ThreadContext.clearAll()\n");
                ThreadContext.clearAll();
            }
        }

    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}