package com.ig.servlet;

import com.ig.Interface.Implements.EmployeeImpl;
import com.ig.Interface.Implements.ManagerImpl;
import com.ig.Interface.Implements.StudentImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SwitchCheck {
    private static final Logger log = LogManager.getLogger();
    private static StudentImpl studentImpl = new StudentImpl();
    private static EmployeeImpl employeeImpl = new EmployeeImpl();
    private static ManagerImpl managerImpl = new ManagerImpl();

    public static void checkk(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        log.info("log:: --- checkk() ---");
        String action = (String) session.getAttribute("action");
        String path = (String) session.getAttribute("path");
        log.info("log:: action ---> " + action);
        log.info("log:: path ---> " + path);
        if(action == null)
            action = "list";
        try {
            if(path.equals("/student")) {
                switch(action) {
                    case "view":
                        studentImpl.viewCourse(request, response);
                        break;
                    case "list":
                    default:
                        studentImpl.listCourse(session, request, response);
                        break;
                }
            }
            else if(path.equals("/employee")) {
                switch(action) {
                    case "view":
                        employeeImpl.viewCourse(request, response);
                        break;
                    case "addStudent":
                        employeeImpl.setLocalId(request.getParameter("courseId"));
                        employeeImpl.addStudentForm(request, response);
                        break;
                    default:
                        employeeImpl.listCourse(request, response);
                        break;
                }
            }
            else if(path.equals("/manager")) {
                switch(action) {
                    case "deleteCourse":
                        managerImpl.deleteCourse(request, response);
                        break;
                    case "addStudent":
                        managerImpl.setLocalId(request.getParameter("courseId"));
                        managerImpl.addStudentForm(request, response);
                        break;
                    case "create":
                        managerImpl.showCourseForm(request, response);
                        break;
                    case "view":
                        managerImpl.viewCourse(request, response);
                        break;
                    case "download":
                        managerImpl.downloadStudent(request, response);
                        break;
                    case "list":
                    default:
                        managerImpl.listCourse(request, response);
                        break;
                }
            }
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
