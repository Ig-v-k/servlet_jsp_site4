package com.ig.dao.interfaces;

import com.ig.model.UserAccount;

import java.util.ArrayList;
import java.util.Map;

public interface DAO_for_User<T> {
    Map<String, UserAccount> getAllDB();
    ArrayList<UserAccount> get_Find_all_suitable();
    UserAccount getUser(String username, String password, int uid);
    UserAccount getUser(int i);
    boolean check_User();
    int get_Number_of_User();
    void insertUser(UserAccount o);
    void deleteUser(T o);
    void updateUser(T o);
}
