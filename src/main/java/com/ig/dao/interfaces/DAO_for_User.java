package com.ig.dao.interfaces;

import com.ig.model.UserAccount;

import java.util.ArrayList;

public interface DAO_for_User<T> {
    ArrayList<UserAccount> get_Find_all_suitable();
    UserAccount getUser(String password, String userName, int i);
    UserAccount getUser(int i);
    void insertUser(UserAccount o);
    int get_Number_of_User();
    boolean check_User();
    void deleteUser(T o);
    void updateUser(T o);
}
