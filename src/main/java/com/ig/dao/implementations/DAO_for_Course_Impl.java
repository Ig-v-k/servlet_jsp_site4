package com.ig.dao.implementations;

import com.ig.dao.between_abstract.RealiseDAO_COURSE;
import com.ig.db.DBCourse;
import com.ig.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class DAO_for_Course_Impl extends RealiseDAO_COURSE {
    private static final Logger log = LogManager.getLogger();
    private HttpServletRequest req;
    private HttpServletResponse res;

    DAO_for_Course_Impl(){};
    DAO_for_Course_Impl(HttpServletRequest request, HttpServletResponse response) {
        this.req = request;
        this.res = response;
    }
    private static Map<Integer, Course> getCourseDatabase() {
        return DBCourse.getCourseDatabase();
    }
    @Override
    public Map<Integer, Course> getAllDB() {
        return getCourseDatabase();
    }
    @Override
    public Course getCourse(String idString) {
        return getCourse(idString, res);
    }
    @Override
    public Course getCourse(String idString, HttpServletResponse response) {
        log.entry(idString);
        if(idString == null || idString.length() == 0) {
            try {
                response.sendRedirect("courses");
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        try {
            Course course = getCourseDatabase().get(Integer.parseInt(idString));
            if(course == null) {
                response.sendRedirect("courses");
                return log.exit(null);
            }
            return log.exit(course);
        }
        catch(Exception e) {
            try {
                response.sendRedirect("courses");
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
            return log.exit(null);
        }
    }
    @Override
    public void updateCourse(String a, Course newValue, int i) {
        Course q = this.getCourse(a);
        getCourseDatabase().replace(i, q, newValue);
    }
    @Override
    public void deleteCourse() {

    }
    @Override
    public void insertCourse() {

    }
}
