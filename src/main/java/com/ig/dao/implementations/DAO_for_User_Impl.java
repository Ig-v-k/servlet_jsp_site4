package com.ig.dao.implementations;

import com.ig.dao.between_abstract.RealiseDAO_USER;
import com.ig.db.DBRole;
import com.ig.model.UserAccount;

import java.util.ArrayList;
import java.util.Map;

public class DAO_for_User_Impl extends RealiseDAO_USER {

    static {
        Map<String, UserAccount> dao_user_database_impl = get_MAP_User_Database();
    }
    private static Map<String, UserAccount> get_MAP_User_Database() {
        return DBRole.get_MAP_User_Database();
    }
    @Override
    public ArrayList<UserAccount> get_Find_all_suitable() {
        return null;
    }
    @Override
    public UserAccount getUser(int i) {
        return null;
    }
    @Override
    public UserAccount getUser(String password, String userName, int i) {
        return null;
    }
    @Override
    public boolean check_User() {
        return false;
    }
    @Override
    public int get_Number_of_User() {
        return 0;
    }
    @Override
    public void deleteUser(String o) {

    }
    @Override
    public void updateUser(String o) {

    }
    @Override
    public void insertUser(UserAccount o) {

    }
}
