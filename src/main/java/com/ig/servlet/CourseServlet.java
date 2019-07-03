package com.ig.servlet;

import com.ig.model.Course;
import com.ig.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet(
            name = "qwe",
            urlPatterns = {"/courses"},
            loadOnStartup = 1
)
public class CourseServlet extends javax.servlet.http.HttpServlet {
    private volatile int COURSE_ID_SEQUENCE = 1;
    private Map<Integer, Course> courseDatabase = new LinkedHashMap<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
                this.showCourseForm(request, response);
                break;
            case "view":
                this.viewCourse(request, response);
                break;
            case "download":
                this.downloadStudent(request, response);
                break;
            case "list":
            default:
                this.listCourse(request, response);
                break;
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException
    {
        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action)
        {
            case "create":
//                System.out.println(request.getParameter("courseName"));
//                System.out.println(request.getParameter("professor"));
//                System.out.println(request.getParameter("student\n"));
                this.createCourse(request, response);
                break;
            case "list":
            default:
                response.sendRedirect("courses");
                break;
        }
    }
    private void showCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/WEB-INF/jsp/view/courseForm.jsp").forward(request, response);
    }
    private void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idString = request.getParameter("courseId");
        Course course = this.getcourse(idString, response);
        if(course == null)
            return;
        request.setAttribute("courseId", idString);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewCourse.jsp").forward(request, response);
    }
    private void downloadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String idString = request.getParameter("courseId");
        Course course = this.getcourse(idString, response);
        if(course == null)
            return;
        String name = request.getParameter("student");
        if(name == null)
        {
            response.sendRedirect("courses?action=view&courseId=" + idString);
            return;
        }
        Student Student = course.getStudent(name);
        if(Student == null)
        {
            response.sendRedirect("courses?action=view&courseId=" + idString);
            return;
        }
        response.setHeader("Content-Disposition", "Student; filename=" + Student.getName());
        response.setContentType("application/octet-stream");
    }
    private void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setAttribute("courseDatabase", this.courseDatabase);
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
    }
    private void createCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

//        System.out.println(request.getParameter("courseName"));
//        System.out.println(request.getParameter("professor"));
//        System.out.println(request.getParameter("student\n"));

        Course course = new Course();
        Student student = new Student();
        course.setName(request.getParameter("courseName"));
        course.setProfessorName(request.getParameter("professor"));
        student.setName(request.getParameter("student"));

//        System.out.println(course.getName());
//        System.out.println(course.getProfessorName());
//        System.out.println(student.getName());

        if(!(student.getName().equals(""))) { // <--- NPE <--- courseForm.jsp - courseName(customerName):11 // <--- NPE
            course.addStudent(student);
        }
        int id;
        synchronized(this)
        {
            id = this.COURSE_ID_SEQUENCE++;
            this.courseDatabase.put(id, course);
        }
        response.sendRedirect("courses?action=view&courseId=" + id);
    }
    private Course getcourse(String idString, HttpServletResponse response) throws ServletException, IOException
    {
        if(idString == null || idString.length() == 0)
        {
            response.sendRedirect("courses");
            return null;
        }
        try
        {
            Course course = this.courseDatabase.get(Integer.parseInt(idString));
            if(course == null)
            {
                response.sendRedirect("courses");
                return null;
            }
            return course;
        }
        catch(Exception e)
        {
            response.sendRedirect("courses");
            return null;
        }
    }
}
