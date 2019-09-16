package com.ig.interfaceFunctions_for_roles.Implements;

import com.ig.dao.implementations.DAO_for_Course_Impl;
import com.ig.db.DBCourse;
import com.ig.model.Course;
import com.ig.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeImpl extends Functions_ImplEmployee {
    private static final Logger log = LogManager.getLogger();
    private Integer i = 1;
    private volatile int COURSE_ID_SEQUENCE = 1;
    private String localId;
    ManagerImpl manager;
    DAO_for_Course_Impl dao_for_course_;

    @Override
    public void addStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = dao_for_course_.getCourse(localId, response);
        request.setAttribute("courseId", localId);
        request.setAttribute("course", course);
        request.setAttribute("courseDatabase", DBCourse.getCourseDatabase());
        request.getRequestDispatcher("/WEB-INF/jsp/view/addStudentForm.jsp").forward(request, response);
    }
    @Override
    public void showCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view/courseForm.jsp").forward(request, response);
    }
    @Override
    public void downloadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager.downloadStudent(request, response);
    }
    @Override
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager.addStudent(request, response);
    }
    @Override
    public void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("log:: --- employee:listCourse() ---");
        log.debug("List courses.");
        request.setAttribute("courseDatabase", DBCourse.getCourseDatabase());
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
        log.info("courseDatabse ---> " + DBCourse.getCourseDatabase());
    }
    @Override
    public void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        manager.viewCourse(request, response);
    }
    public void setLocalId(String localId) {
        this.localId = localId;
    }
}
