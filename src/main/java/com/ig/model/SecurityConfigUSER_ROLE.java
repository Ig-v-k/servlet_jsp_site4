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
        List<String> urlPatterns1 = new ArrayList<String>();
        // Configuration for: EMPLOYEE
        List<String> urlPatterns2 = new ArrayList<String>();
        // Configuration for: MANAGER
        List<String> urlPatterns3 = new ArrayList<String>();

        urlPatterns1.add("/student");
        urlPatterns1.add("/login");

        urlPatterns2.add("/employee");
        urlPatterns2.add("/session");
        urlPatterns2.add("/login");

        urlPatterns3.add("/manager");
        urlPatterns3.add("/session");
        urlPatterns3.add("/login");

        mapConfig.put(SECURITY_ROLE_USER_STUDENT, urlPatterns1);
        mapConfig.put(SECURITY_ROLE_USER_EMPLOYEE, urlPatterns2);
        mapConfig.put(SECURITY_ROLE_USER_MANAGER, urlPatterns3);
    }
    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }
    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
