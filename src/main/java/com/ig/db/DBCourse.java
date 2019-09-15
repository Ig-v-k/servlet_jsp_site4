package com.ig.db;

import com.ig.model.Course;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBCourse {
    private static Map<Integer, Course> courseDatabase = new LinkedHashMap<>();

    public static Map<Integer, Course> getCourseDatabase() {
        return courseDatabase;
    }
}
