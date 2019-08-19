package com.ig.servlet.filters;

import com.ig.model.UserAccount;
import com.ig.servlet.AppUtils;
import com.ig.servlet.CheckROLE_URL;
import com.ig.servlet.DBdao;
import com.ig.servlet.UserRoleRequestWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuthenticationFilter implements Filter {
    private static final Logger log = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("log:: doFilter()");
        HttpSession session = ((HttpServletRequest)request).getSession();
        String servletPath = ((HttpServletRequest) request).getServletPath();
        if (servletPath.equals("/login")) {
            log.info("log:: chain.doFilter()");
            chain.doFilter(request, response);
        }
        else {
            if(session == null) {
                log.info("log:: session=null - response.sendRedirect(login)");
                ((HttpServletResponse)response).sendRedirect("login");
                chain.doFilter(request, response);
            }
            else {
                HttpServletRequest wrapRequest = ((HttpServletRequest) request);
                log.info("log:: servletPath ---> " + servletPath);
                log.info("log:: CheckROLE_URL.isSecurityPage() ---> " + CheckROLE_URL.isSecurityPage(((HttpServletRequest) request)));
                if (CheckROLE_URL.isSecurityPage(((HttpServletRequest) request))) {
                    UserAccount loginedUser = AppUtils.getLoginedUser(((HttpServletRequest) request).getSession());
                    log.info("log:: loginedUser ---> " + loginedUser);
                    if (loginedUser == null) {
                        String requestUri = ((HttpServletRequest) request).getRequestURI();
                        int redirectId = AppUtils.storeRedirectAfterLoginUrl(((HttpServletRequest) request).getSession(), requestUri);
                        log.info("log:: response.sendRedirect(/login) ---> ");
                        ((HttpServletResponse) response).sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                        chain.doFilter(wrapRequest, response);
                    }
                    else {
                        String userName = loginedUser.getUserName();
                        List<String> roles = loginedUser.getRoles();
                        wrapRequest = new UserRoleRequestWrapper(userName, roles, ((HttpServletRequest) request));
                    }
                    boolean hasPermission = CheckROLE_URL.hasPermission(wrapRequest);
                    if (!hasPermission) {
                        log.info("log:: response.sendRedirect(/course) ---> ");
                        ((HttpServletResponse) response).sendRedirect("courses");
                    }
                }
                log.info("log:: chain.doFilter()");
                chain.doFilter(wrapRequest, response);
            }
        }
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

    }

    public AuthenticationFilter() {
    }

    @Override
    public void destroy() {
    }
}
