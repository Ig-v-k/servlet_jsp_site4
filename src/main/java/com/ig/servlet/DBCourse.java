package com.ig.servlet;

import com.ig.model.Course;

import java.util.LinkedHashMap;
import java.util.Map;

public class DBCourse {
    private static Map<Integer, Course> courseDatabase = new LinkedHashMap<>();

    public static Map<Integer, Course> getCourseDatabase() {
        return courseDatabase;
    }
    public static void setCourseDatabase(Map<Integer, Course> courseDatabase) {
        DBCourse.courseDatabase = courseDatabase;
    }
}
