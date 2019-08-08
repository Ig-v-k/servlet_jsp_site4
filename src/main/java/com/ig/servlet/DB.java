package com.ig.servlet;

import com.ig.model.Manager;
import com.ig.model.Professor;
import com.ig.model.Student;

import java.util.LinkedHashMap;
import java.util.Map;

class DBdao {
    private static final Map<String, Student> userSTUDENT_RoleDatabase = new LinkedHashMap<String, Student>();
    private static final Map<String, Professor> userEMPLOYEE_RoleDatabase = new LinkedHashMap<String, Professor>();
    private static final Map<String, Manager> userMANAGER_RoleDatabase = new LinkedHashMap<String, Manager>();

    static {
        initDB();
    }
    private static void initDB() {
        Student student = new Student("student");
    }
}
