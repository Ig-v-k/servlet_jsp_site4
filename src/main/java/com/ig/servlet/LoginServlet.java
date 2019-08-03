package com.ig.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

@WebServlet(
        name = "loginServlet",
        urlPatterns = "/login"
)
public class LoginServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private static final Map<String, String> userDatabase = new Hashtable<>();
    static {
        userDatabase.put("Nicholas", "1");
        userDatabase.put("Sarah", "12");
        userDatabase.put("Mike", "123");
        userDatabase.put("John", "1234");
    }
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
        request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("username") != null) {
            response.sendRedirect("courses");
            return;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if(username == null || password == null || !LoginServlet.userDatabase.containsKey(username) || !password.equals(LoginServlet.userDatabase.get(username))) {
            log.warn("Login failed for user {}", username);
            request.setAttribute("loginFailed", true);
            request.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(request, response);
        }
        else {
            log.info("User {} successfully logged in.", username);
            session.setAttribute("username", username);
            request.getRequestedSessionId();
            response.sendRedirect("courses");
        }
    }
}
