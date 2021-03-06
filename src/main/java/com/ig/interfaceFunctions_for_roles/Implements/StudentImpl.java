package com.ig.interfaceFunctions_for_roles.Implements;

import com.ig.db.DBCourse;
import com.ig.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class StudentImpl extends Functions_ImplStudent{
    private static final Logger log = LogManager.getLogger();
    EmployeeImpl employee;

    @Override
    public void listCourse(HttpSession session , HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("log:: --- student:listCourse() ---");
        log.debug("List courses.");
        request.setAttribute("courseDatabase", DBCourse.getCourseDatabase());
        request.setAttribute("username", session.getAttribute("username"));
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
        log.info("courseDatabse ---> " + DBCourse.getCourseDatabase());
    }
    @Override
    public void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employee.viewCourse(request, response);
    }
}
