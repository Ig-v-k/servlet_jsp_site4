package com.ig.interfaceFunctions_for_roles.Implements;

import com.ig.interfaceFunctions_for_roles.IFunctions_forEmployee;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Functions_ImplEmployee implements IFunctions_forEmployee {
    @Override
    public void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void addStudentForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void showCourseForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void downloadStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void addStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
