package com.ig.dao.between_abstract;

import com.ig.dao.interfaces.DAO_for_User;
import com.ig.db.DBRole;

public abstract class RealiseDAO_USER implements DAO_for_User<String> {
    public DBRole realiseDBRole_for_dao = new DBRole();
}
