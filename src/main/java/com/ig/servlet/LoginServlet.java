package com.ig.servlet;

import com.ig.dao.implementations.DAO_for_Course_Impl;
import com.ig.dao.implementations.DAO_for_User_Impl;
import com.ig.utils.AppUtils;
import com.ig.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login",
        loadOnStartup = 1
)
public class LoginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private DAO_for_Course_Impl dao_for_course_;
    private DAO_for_User_Impl dao_for_user_;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        log.info("log:: --- doGet() --- ");
        log.info("log:: doGet() --- session.getAttribute(\"username\") ---> " + session.getAttribute("username"));
        if(request.getParameter("logout") != null) {
            if(log.isDebugEnabled()) {
                log.debug("User {} logged out.", session.getAttribute("username"));
            }
            session.invalidate();
            response.sendRedirect("login");
            return;
        }
        else if(session.getAttribute("username") != null) {
            log.info("log:: doGet() --- response.sendRedirect(courses)");
            response.sendRedirect("courses");
            return;
        }
        request.setAttribute("loginFailed", false);
        request.setAttribute("uidloginFailed", false);
        request.setAttribute("db", this.dao_for_course_.getAllDB());
        log.info("log:: request.getRequestDispatcher(.../login.jsp)");
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("log:: --- doPost() ---");
        HttpSession session = request.getSession(false);
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String uid = request.getParameter("uid");
        log.info("request.getParameter(\"username\") ---> " + request.getParameter("username"));
        log.info("request.getParameter(\"password\") ---> " + request.getParameter("password"));
        log.info("request.getParameter(\"uid\") ---> " + request.getParameter("uid"));
        UserAccount user = this.dao_for_user_.getUser(username, password, Integer.parseInt(uid));
        if (user == null) {
            request.setAttribute("loginFailedEmpty", true);
            request.setAttribute("db", this.dao_for_course_.getAllDB());
            log.info("log:: doPost() --- request.getRequestDispatcher(.../login.jsp)");
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
            return;
        }
        else {
            log.info("log:: put UserAccount ---> AppUtils.storeLoginedUser()");
            if(username.equals(this.dao_for_user_.getAllDB().get(username).getUserName())) {
                AppUtils.storeLoginedUser(session, this.dao_for_user_.getAllDB().get(username));
//                request.changeSessionId();
            }
        }
//        if(uid.equals("") || username.equals("") || password.equals("")) {
//            log.warn("log:: Login failed for user: {} - {} - {}", username, password, uid);
//            request.setAttribute("loginFailedEmpty", true);
//            request.setAttribute("db", DBdao.get_MAP_User_Database());
//            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
//        }
//        else {
//            if(!DBdao.get_MAP_User_Database().get(username).getUserName().contains(username)) {
//                log.warn("log:: : username : failed for user: {}", username);
//                request.setAttribute("loginFailed", true);
//                request.setAttribute("db", DBdao.get_MAP_User_Database());
//                request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
//            }
//            else if(!password.equals(DBdao.get_MAP_User_Database().get(username).getPassword())) {
//                log.warn("log:: : password : failed for user: {}", username);
//                request.setAttribute("loginFailed", true);
//                request.setAttribute("db", DBdao.get_MAP_User_Database());
//                request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
//            }
//            else if(DBdao.get_MAP_User_Database().get(username).getUid() != Integer.parseInt(uid)) {
//                log.warn("log:: : uid : failed for user: {}", username);
//                request.setAttribute("uidloginFailed", true);
//                request.setAttribute("db", DBdao.get_MAP_User_Database());
//                request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
//            }
//            log.warn("log:: User: {} successfully logged in.", username);
//            AppUtils.storeLoginedUser(request.getSession(), user);
//            log.info("log:: session user ---> " + AppUtils.getLoginedUser(request.getSession()));
//            session.setAttribute("username", username);
//            session.setAttribute("uid", uid);
//            request.getRequestedSessionId();
//        }
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        }
        catch (Exception e) {
            log.info("log:: EXEPTION:" + e);
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        log.info("log:: requestUri ---> " + requestUri);
        if (requestUri != null) {
            log.info("log:: sendRedirect(requestUri)");
            response.sendRedirect(requestUri);
        }
        else {
            log.info("log:: sendRedirect(courses + username)");
            response.sendRedirect(request.getContextPath() + "/courses/" + username);
        }
    }
}
