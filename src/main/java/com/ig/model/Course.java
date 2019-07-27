package com.ig.model;

import sun.security.jca.GetInstance;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Course {
    private String userName = null;
    private String name = null;
    private Professor professor = new Professor();
    private GetInstance.Instance dateFormat;
    private Map<String, Student> students = new LinkedHashMap<>();

    public Student getStudent(String name) {
        return this.students.get(name);
    }
    public Collection<Student> getStudent() {
        return this.students.values();
    }
    public void addStudentt(Student student) {
        this.students.put(student.getName(), student);
    }
    public int getNumberOfStudent() {
        return this.students.size();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Professor getProfessor() {
        return professor;
    }
    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    public void setProfessorName(String name) {
        this.professor.setName(name); // <--- NPE <--- new Professor();
    }
    public String getProfessorName() {
        return this.professor.getName();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public GetInstance.Instance getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(GetInstance.Instance dateFormat) {
        this.dateFormat = dateFormat;
    }
}
