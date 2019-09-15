package com.ig.dao.between_abstract;

import com.ig.dao.interfaces.DAO_for_Course;
import com.ig.db.DBCourse;

public abstract class RealiseDAO_COURSE implements DAO_for_Course {
    public DBCourse realiseDBRole_for_dao = new DBCourse();
}
