package com.ig.Interface.Implements;

import com.ig.Interface.IFunctions_forStudent;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public abstract class Functions_ImplStudent implements IFunctions_forStudent {
    public abstract void listCourse(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public abstract void viewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
