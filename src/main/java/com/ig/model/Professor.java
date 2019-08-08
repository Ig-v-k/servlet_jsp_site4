package com.ig.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Professor {
    private String name;
    private List<String> roles;

    public Professor(String name) {
        this.name = name;
    }
    public Professor(String name, String... roles) {
        this.name = name;
        this.roles = new ArrayList<String>();
        if (roles != null) {
            this.roles.addAll(Arrays.asList(roles));
        }
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
