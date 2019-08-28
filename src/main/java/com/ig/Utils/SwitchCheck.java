package com.ig.Utils;

import com.ig.Interface.Implements.EmployeeImpl;
import com.ig.Interface.Implements.ManagerImpl;
import com.ig.Interface.Implements.StudentImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SwitchCheck {
    private static StudentImpl studentImpl = new StudentImpl();
    private static EmployeeImpl employeeImpl = new EmployeeImpl();
    private static ManagerImpl managerImpl = new ManagerImpl();

    public SwitchCheck(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        init(session, request, response);
    }
    private static void init(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        String action = (String) session.getAttribute("action");
        String path = (String) session.getAttribute("path");
        if(action == null)
            action = "list";
        try {
            if(path.equals("/student")) {
                switch(action) {
                    case "view":
                        studentImpl.viewCourse(request, response);
                        break;
                    default:
                        studentImpl.listCourse(request, response);
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
