package com.ig.servlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "ServletROLE_STUDENT",
        urlPatterns = {"/student"},
        loadOnStartup = 2
)
public class ServletROLE_STUDENT extends HttpServlet {
    private static final Logger log = LogManager.getLogger();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("log:: --- doGet() ---");
//        resp.sendRedirect(req.getContextPath() + "/courses/" + req.getServletPath());
        req.getRequestDispatcher("/WEB-INF/jsp/view/login.jsp").forward(req, resp);
    }
}
