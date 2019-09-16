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

public class ManagerImpl extends Functions_ImplManager {
    private static final Logger log = LogManager.getLogger();
    private Integer i = 1;
    private volatile int COURSE_ID_SEQUENCE = 1;
    private String localId;
    DAO_for_Course_Impl dao_for_course_;

    @Override
    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBCourse.getCourseDatabase().remove(Integer.parseInt(request.getParameter("courseId")));
        response.sendRedirect("courses?action=view&courseId" + request.getParameter("courseId"));
    }
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
        String idString = request.getParameter("courseId");
        log.entry(idString);
        Course course = dao_for_course_.getCourse(idString, response);
        if(course == null)
            return;
        String name = request.getParameter("student");
        if(name == null) {
            response.sendRedirect("courses?action=view&courseId=" + idString);
            return;
        }
        Student Student = course.getStudent(name);
        if(Student == null) {
            log.info("Requested student {} not found on course {}.", name, idString);
            response.sendRedirect("courses?action=view&courseId=" + idString);
            return;
        }
        response.setHeader("Content-Disposition", "Student; filename=" + Student.getName());
        response.setContentType("application/octet-stream");
        log.exit();
    }
    @Override
    public void createCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.entry();
        Course course = new Course();
        course.setUserName((String)request.getSession().getAttribute("username"));
        Student student = new Student(request.getParameter("student"));
        course.setName(request.getParameter("courseName"));
        course.setProfessorName(request.getParameter("professor"));
        if(!(student.getName().equals(""))) { // <--- NPE <--- courseForm.jsp - courseName(customerName):11 // <--- NPE
            course.addStudentToCourse(student);
        }
        int id;
        synchronized(this) {
            id = this.COURSE_ID_SEQUENCE++;
            DBCourse.getCourseDatabase().put(id, course);
        }
        response.sendRedirect("courses?action=view&courseId=" + id);
        log.exit();
    }
    @Override
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int ii = this.i;
        log.debug("-- start debug --");
        log.info("---: " + ii++ + " :---");
        log.info("studname ---> " + request.getParameter("studname"));
        String studname = request.getParameter("studname");
        log.info("isTrueName --> " + DBCourse.getCourseDatabase().get(Integer.parseInt(localId)).isTrueName(studname));
        if(DBCourse.getCourseDatabase().get(Integer.parseInt(localId)).isTrueName(studname)) {
            request.setAttribute("nameFailed", true);
            addStudentForm(request, response);
        }
        Student student = new Student(studname);
        DBCourse.getCourseDatabase().get(Integer.parseInt(localId)).addStudentToCourse(student);
        response.sendRedirect("courses?action=view&courseId=" + localId);
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
    }
    @Override
    public void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("log:: --- manager:listCourse() ---");
        log.debug("List courses.");
        request.setAttribute("courseDatabase", DBCourse.getCourseDatabase());
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
        log.info("courseDatabse ---> " + DBCourse.getCourseDatabase());
    }
    @Override
    public void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("courseId");
        log.entry(idString);
        Course course = DBCourse.getCourseDatabase().get(Integer.parseInt(idString));
        if(course == null)
            return;
        request.setAttribute("courseId", idString);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewCourse.jsp").forward(request, response);
        log.exit();
    }
    public void setLocalId(String localId) {
        this.localId = localId;
    }
}
