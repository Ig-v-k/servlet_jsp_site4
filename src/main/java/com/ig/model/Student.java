package com.ig.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private String name;
    private Integer age;
    private List<String> roles;

    public Student(String name) {
        this.name = name;
    }
    public Student(String name, String... roles) {
        this.name = name;
        this.roles = new ArrayList<String>();
        if (roles != null) {
            this.roles.addAll(Arrays.asList(roles));
        }
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name;
    }
}
