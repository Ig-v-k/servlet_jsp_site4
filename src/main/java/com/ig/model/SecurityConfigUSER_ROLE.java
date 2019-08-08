package com.ig.model;

import java.util.*;

public class SecurityConfigUSER_ROLE {
    private static final String SECURITY_ROLE_USER_STUDENT = "STUDENT";
    private static final String SECURITY_ROLE_USER_EMPLOYEE = "EMPLOYEE";
    private static final String SECURITY_ROLE_USER_MANAGER = "MANAGER";

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
        urlPatterns2.add("/employee");
        urlPatterns3.add("/manager");
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
