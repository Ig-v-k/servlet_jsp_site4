package com.ig.servlet;

import com.ig.servlet.listeners.SessionRegistry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "sessionListServlet")
public class SessionListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        request.setAttribute("numberOfSessions", SessionRegistry.getNumberOfSession());
        request.setAttribute("sessionList", SessionRegistry.getAllSession());
        request.getRequestDispatcher("/WEB-INF/jsp/view/sessions.jsp").forward(request, response);
    }
}
