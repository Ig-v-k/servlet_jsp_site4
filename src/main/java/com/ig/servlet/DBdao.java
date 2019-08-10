package com.ig.servlet;

import com.ig.model.SecurityConfigUSER_ROLE;
import com.ig.model.UserAccount;

import java.util.LinkedHashMap;
import java.util.Map;

class DBdao {
    private static final Map<String, UserAccount> user_RoleDatabase = new LinkedHashMap<String, UserAccount>();

    static {
        initDB();
    }
    private static void initDB() {
        UserAccount s = new UserAccount(1, "student", "123", SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_STUDENT);
        UserAccount e = new UserAccount(2, "employee", "123", SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_EMPLOYEE, SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_STUDENT);
        UserAccount m = new UserAccount(3, "manager", "123", SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_MANAGER, SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_EMPLOYEE, SecurityConfigUSER_ROLE.SECURITY_ROLE_USER_STUDENT);
        user_RoleDatabase.put(s.getUserName(), s);
        user_RoleDatabase.put(e.getUserName(), e);
        user_RoleDatabase.put(m.getUserName(), m);
    }
    static UserAccount findUser(String userName, String password) {
        UserAccount u = user_RoleDatabase.get(userName);
        if (u != null && u.getPassword().equals(password)) {
            return u;
        }
        return null;
    }
    static Map<String, UserAccount> get_MAP_User_RoleDatabase() {
        return user_RoleDatabase;
    }
}
