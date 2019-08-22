package com.ig.I;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Functions {
    void listCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
