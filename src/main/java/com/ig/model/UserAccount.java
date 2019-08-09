package com.ig.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserAccount {
    private int uid;
    private String userName;
    private String password;
    private List<String> roles;

    public String getUserName() { return userName; }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<String> getRoles() {
        return roles;
    }
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public int getUid() { return uid; }
    public void setUid(int uid) { this.uid = uid; }
    UserAccount() {}
    public UserAccount(int uid, String userName, String password, String... roles) {
        this.uid = uid;
        this.userName = userName;
        this.password = password;

        this.roles = new ArrayList<String>();
        if (roles != null) {
            this.roles.addAll(Arrays.asList(roles));
        }
    }
}
