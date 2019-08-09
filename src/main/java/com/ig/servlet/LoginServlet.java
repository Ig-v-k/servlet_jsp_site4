package com.ig.servlet;

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
import java.util.Objects;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private static UserAccount a;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(request.getParameter("logout") != null) {
            if(log.isDebugEnabled())
                log.debug("User {} logged out.", session.getAttribute("username"));
            session.invalidate();
            response.sendRedirect("login");
            return;
        }
        else if(session.getAttribute("username") != null) {
            response.sendRedirect("courses");
            return;
        }
        request.setAttribute("loginFailed", false);
        request.setAttribute("uidloginFailed", false);
        request.setAttribute("db", DBdao.get_MAP_User_RoleDatabase());
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("log:: --- doPost() ---");
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            response.sendRedirect("courses");
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int uid = Integer.parseInt(request.getParameter("uid"));
        if(!(uid == 1 || uid == 2 || uid == 3) || username == null || password == null || !Objects.requireNonNull(DBdao.findUser(username, password)).getUserName().contains(username) || !password.equals(Objects.requireNonNull(DBdao.findUser(username, password)).getUserName())) {
            log.info("log:: if()");
            log.warn("log:: Login failed for user: {}", username);
            request.setAttribute("loginFailed", true);
            request.setAttribute("db", DBdao.get_MAP_User_RoleDatabase());
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
        }
        else {
            if(DBdao.get_MAP_User_RoleDatabase().get(username).getUid() != uid) {
                log.warn("log:: : UID : failed for user: {}", username);
                request.setAttribute("uidloginFailed", true);
                request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
            }
            log.info("log:: User: {} successfully logged in.", username);
            session.setAttribute("username", username);
            request.getRequestedSessionId();
            response.sendRedirect("courses");
        }
    }
}
