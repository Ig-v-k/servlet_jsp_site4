package com.ig.servlet;

import com.ig.Utils.SwitchCheck;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
            name = "courses",
            urlPatterns = {"/courses/*"},
            loadOnStartup = 1
)
public class CourseServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private static SwitchCheck switcheck = null;
    private HttpSession session = null;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("GET request received.");
        session = request.getSession(false);
        session.setAttribute("path", request.getServletPath());
        session.setAttribute("action", request.getParameter("action"));
        new SwitchCheck(session, request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        log.debug("POST request received.");
        if(request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        this.doGet(request, response);
    }
}
