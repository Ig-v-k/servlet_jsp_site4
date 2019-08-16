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
            chain.doFilter(request, response);
            return;
        }
        if(session == null || session.getAttribute("username") == null ||session.getAttribute("password") == null || session.getAttribute("uid") == null) {
            ((HttpServletResponse)response).sendRedirect("login");
        }
        UserAccount loginedUser = AppUtils.getLoginedUser(((HttpServletRequest) request).getSession());
        HttpServletRequest wrapRequest = ((HttpServletRequest) request);
        log.info("log:: servletPath ---> " + servletPath);
        log.info("log:: loginedUser ---> " + loginedUser);
        if (loginedUser != null) {
            String userName = loginedUser.getUserName();
            List<String> roles = loginedUser.getRoles();
            wrapRequest = new UserRoleRequestWrapper(userName, roles, ((HttpServletRequest) request));
        }
        log.info("log:: CheckROLE_URL.isSecurityPage() ---> " + CheckROLE_URL.isSecurityPage(((HttpServletRequest) request)));
        if (CheckROLE_URL.isSecurityPage(((HttpServletRequest) request))) {
            if (loginedUser == null) {
                String requestUri = ((HttpServletRequest) request).getRequestURI();
                int redirectId = AppUtils.storeRedirectAfterLoginUrl(((HttpServletRequest) request).getSession(), requestUri);
                log.info("log:: response.sendRedirect(/login) ---> ");
                ((HttpServletResponse) response).sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                chain.doFilter(wrapRequest, response);
                return;
            }
            boolean hasPermission = CheckROLE_URL.hasPermission(wrapRequest);
            if (!hasPermission) {
                log.info("log:: response.sendRedirect(/course) ---> ");
                ((HttpServletResponse) response).sendRedirect("courses");
                return;
            }
        }
        chain.doFilter(wrapRequest, response);
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
