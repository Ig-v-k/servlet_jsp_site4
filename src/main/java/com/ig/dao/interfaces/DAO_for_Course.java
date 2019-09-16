package com.ig.dao.interfaces;

import com.ig.model.Course;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface DAO_for_Course {
    Map<Integer, Course> getAllDB();
    Course getCourse(String a);
    Course getCourse(String idString, HttpServletResponse response);
    void updateCourse(String a, Course newValue, int i);
    void deleteCourse();
    void insertCourse();

}
