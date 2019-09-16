package com.ig.dao.implementations;

import com.ig.dao.between_abstract.RealiseDAO_USER;
import com.ig.db.DBRole;
import com.ig.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Map;

public class DAO_for_User_Impl extends RealiseDAO_USER {
    private static final Logger log = LogManager.getLogger();
    private HttpServletRequest req;
    private HttpServletResponse res;

    DAO_for_User_Impl() {}
    DAO_for_User_Impl(HttpServletRequest request, HttpServletResponse response) {
        this.req = request;
        this.res = response;
    }
    private static Map<String, UserAccount> get_MAP_User_Database() {
        return DBRole.getUserDatabase();
    }
    @Override
    public Map<String, UserAccount> getAllDB() {
        return get_MAP_User_Database();
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
    public UserAccount getUser(String username, String password, int uid) {
        UserAccount u = getAllDB().get(username);
        if (u != null && u.getPassword().equals(password) && u.getUid() == uid) {
            return u;
        }
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
