package com.ig.DB;

import com.ig.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBCourse {
    private static final Logger log = LogManager.getLogger();
    private static Map<Integer, Course> courseDatabase = new LinkedHashMap<>();

    public static Map<Integer, Course> getCourseDatabase() {
        return courseDatabase;
    }
    public static void setCourseDatabase(Map<Integer, Course> courseDatabase) {
        DBCourse.courseDatabase = courseDatabase;
    }
    public static Course getcourseOfMap(String idString, HttpServletResponse response) throws IOException {
        log.entry(idString);
        if(idString == null || idString.length() == 0) {
            response.sendRedirect("courses");
            return null;
        }
        try {
            Course course = courseDatabase.get(Integer.parseInt(idString));
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
