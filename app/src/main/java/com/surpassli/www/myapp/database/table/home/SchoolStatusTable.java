package com.surpassli.www.myapp.database.table.home;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolStatusTable 校园动态
 */

public class SchoolStatusTable {

    public static final String NAME = "SchoolStatusTable";

    public static final String SCHOOL_STATUS_URL = "url";

    public static final String SCHOOL_STATUS_TITLE = "title";

    public static final String MARK_ID = "mark_id";

    public static final String SCHOOL_STATUS_TIME = "time";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            MARK_ID + " text, " +
            SCHOOL_STATUS_TITLE + " text, " +
            SCHOOL_STATUS_TIME + " text, "+
            SCHOOL_STATUS_URL + " text)";
}
