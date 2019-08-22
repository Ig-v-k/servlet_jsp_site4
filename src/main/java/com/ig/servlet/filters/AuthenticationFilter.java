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
        HttpSession session = ((HttpServletRequest)request).getSession();
        String servletPath = ((HttpServletRequest) request).getServletPath();
        HttpServletRequest wrapRequest = ((HttpServletRequest) request);

        log.info("log:: doFilter()");
        log.info("log:: session ---> " + session);
        log.info("log:: servletPath ---> " + servletPath);
        log.info(servletPath.equals("/login") ? "log:: servletPath.equals(\"/login\") ---> true" : "log:: servletPath.equals(\"/login\") ---> false");
        if (servletPath.equals("/login")) {
            log.info("log:: chain.doFilter()");
            chain.doFilter(request, response);
            return;
        }
        else {
            if(AppUtils.getLoginedUser(session) == null) {
                log.info("log:: session.getAttribute(\"username\") = null ---> reseponse.sendRedirect(login)");
                ((HttpServletResponse)response).sendRedirect("login");
                return;
            }
            log.info("log:: CheckROLE_URL.isSecurityPage() ---> " + CheckROLE_URL.isSecurityPage(((HttpServletRequest) request)));
            if (CheckROLE_URL.isSecurityPage(((HttpServletRequest) request))) {
                UserAccount loginedUser = AppUtils.getLoginedUser(((HttpServletRequest) request).getSession());
                log.info("log:: loginedUser ---> " + loginedUser);
                if (loginedUser == null) {
                    String requestUri = ((HttpServletRequest) request).getRequestURI();
                    int redirectId = AppUtils.storeRedirectAfterLoginUrl(((HttpServletRequest) request).getSession(), requestUri);
                    log.info("log:: redirectId ---> " + redirectId);
                    log.info("log:: response.sendRedirect(/login)");
                    ((HttpServletResponse) response).sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + redirectId);
                    return;
                }
                else {
                    String userName = loginedUser.getUserName();
                    List<String> roles = loginedUser.getRoles();
                    wrapRequest = new UserRoleRequestWrapper(userName, roles, ((HttpServletRequest) request));
                }
                boolean hasPermission = CheckROLE_URL.hasPermission(wrapRequest);
                log.info("log:: hasPermission ---> " + hasPermission);
                if (!hasPermission) {
                    log.info("log:: response.sendRedirect(/course)");
                    ((HttpServletResponse) response).sendRedirect("courses");
                    return;
                }
            }
            else {
                log.info("log:: response.sendRedirect(login)");
                ((HttpServletResponse) response).sendRedirect("login");
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
