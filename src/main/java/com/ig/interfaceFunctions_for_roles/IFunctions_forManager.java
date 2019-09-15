package com.ig.interfaceFunctions_for_roles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IFunctions_forManager {
    void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void addStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void showCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void downloadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void createCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
