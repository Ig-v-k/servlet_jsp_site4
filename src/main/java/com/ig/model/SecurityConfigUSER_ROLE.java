package com.ig.model;

import java.util.*;

public class SecurityConfigUSER_ROLE {
    public static final String SECURITY_ROLE_USER_STUDENT = "STUDENT";
    public static final String SECURITY_ROLE_USER_EMPLOYEE = "EMPLOYEE";
    public static final String SECURITY_ROLE_USER_MANAGER = "MANAGER";
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }
    private static void init() {
        // Configuration for: STUDENT
        List<String> student = new ArrayList<String>();
        // Configuration for: EMPLOYEE
        List<String> employee = new ArrayList<String>();
        // Configuration for: MANAGER
        List<String> manager = new ArrayList<String>();

        student.add("/student");
        student.add("/login");

        employee.add("/employee");
        employee.add("/session");
        employee.add("/login");
        employee.add("/chat");

        manager.add("/manager");
        manager.add("/session");
        manager.add("/login");
        manager.add("/chat");

        mapConfig.put(SECURITY_ROLE_USER_STUDENT, student);
        mapConfig.put(SECURITY_ROLE_USER_EMPLOYEE, employee);
        mapConfig.put(SECURITY_ROLE_USER_MANAGER, manager);
    }
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
