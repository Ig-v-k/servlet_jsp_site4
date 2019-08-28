package com.ig.Interface.Implements;

import com.ig.DB.DBCourse;
import com.ig.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StudentImpl extends Functions_ImplStudent{
    private static final Logger log = LogManager.getLogger();

    @Override
    public void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
}
