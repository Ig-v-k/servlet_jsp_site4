package com.ig.servlet;

import com.ig.model.Course;
import com.ig.model.Student;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger log = LogManager.getLogger();
    private volatile int COURSE_ID_SEQUENCE = 1;
    private Map<Integer, Course> courseDatabase = new LinkedHashMap<>();
    private String localId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("GET request received.");
        String action = request.getParameter("action");
        if(action == null)
            action = "list";
        switch(action) {
            case "deleteCourse":
                this.deleteCourse(request, response);
                break;
            case "addStudent":
                localId = request.getParameter("courseId");
                this.addStudentForm(request, response);
                break;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        log.debug("POST request received.");
        if(request.getSession().getAttribute("username") == null) {
            response.sendRedirect("login");
            return;
        }
        String action = request.getParameter("action");
        if(action == null)
        action = "list";
        switch(action) {
            case "create":
            this.createCourse(request, response);
            break;
            case "addStudent":
            this.addStudent(request, response);
            break;
            case "list":
            default:
            response.sendRedirect("courses");
            break;
        }
    }
    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.courseDatabase.remove(Integer.parseInt(request.getParameter("courseId")));
        response.sendRedirect("courses?action=view&courseId" + request.getParameter("courseId"));
    }
    private void addStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Course course = this.getcourseOfMap(localId, response);
        request.setAttribute("courseId", localId);
        request.setAttribute("course", course);
        request.setAttribute("courseDatabase", this.courseDatabase);
        request.getRequestDispatcher("/WEB-INF/jsp/view/addStudentForm.jsp").forward(request, response);
    }
    private void addStudent(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("------- addStudent -------");
        log.info("Attribute ---> studname ---> ", request.getAttribute("studname"));
        log.info("Parameter ---> studname ---> ", request.getParameter("studname"));
//        String studname = request.getParameter("studname");
//        String studnameOfDatabase = this.courseDatabase.get(Integer.parseInt(localId)).getStudent(studname).getName();
//        log.info("addStudent method ---> studnameOfDatabase ---> ", studnameOfDatabase);
//        if(studname.equalsIgnoreCase(studnameOfDatabase)) {
//            request.setAttribute("nameFailed", true);
//            addStudentForm(request, response);
//        }
//        Student s = new Student(studname);
//        this.courseDatabase.get(Integer.parseInt(localId)).addStudentToCourse(s);
//        response.sendRedirect("courses?action=view&courseId=" + localId);
//        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
    }
    private void showCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/view/courseForm.jsp").forward(request, response);
    }
    private void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idString = request.getParameter("courseId");
        log.entry(idString);
        Course course = this.getcourseOfMap(idString, response);
        if(course == null)
            return;
        request.setAttribute("courseId", idString);
        request.setAttribute("course", course);
        request.getRequestDispatcher("/WEB-INF/jsp/view/viewCourse.jsp").forward(request, response);
        log.exit();
    }
    private void downloadStudent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String idString = request.getParameter("courseId");
        log.entry(idString);
        Course course = this.getcourseOfMap(idString, response);
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
    private void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("List courses.");
        request.setAttribute("courseDatabase", this.courseDatabase);
        request.getRequestDispatcher("/WEB-INF/jsp/view/listCourse.jsp").forward(request, response);
    }
    private void createCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        System.out.println(request.getParameter("courseName"));
//        System.out.println(request.getParameter("professor"));
//        System.out.println(request.getParameter("student\n"));
        log.entry();

        Course course = new Course();
        course.setUserName((String)request.getSession().getAttribute("username"));
        Student student = new Student(request.getParameter("student"));
        course.setName(request.getParameter("courseName"));
        course.setProfessorName(request.getParameter("professor"));

//        System.out.println(course.getName());
//        System.out.println(course.getProfessorName());
//        System.out.println(student.getName());

        if(!(student.getName().equals(""))) { // <--- NPE <--- courseForm.jsp - courseName(customerName):11 // <--- NPE
            course.addStudentToCourse(student);
        }
        int id;
        synchronized(this) {
            id = this.COURSE_ID_SEQUENCE++;
            this.courseDatabase.put(id, course);
        }
        response.sendRedirect("courses?action=view&courseId=" + id);
        log.exit();
    }
    private Course getcourseOfMap(String idString, HttpServletResponse response) throws IOException {
        log.entry(idString);
        if(idString == null || idString.length() == 0) {
            response.sendRedirect("courses");
            return null;
        }
        try {
            Course course = this.courseDatabase.get(Integer.parseInt(idString));
            if(course == null) {
                response.sendRedirect("courses");
                return log.exit(null);
            }
            return log.exit(course);
        }
        catch(Exception e) {
            response.sendRedirect("courses");
            return log.exit(null);
        }
    }
}
