package com.surpassli.www.myapp.database.table.home;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolStatusTable 校园动态
 */

public class SchoolTrendsTable {

    public static final String NAME = "SchoolTrendsTable";

    public static final String SCHOOL_TRENDS_URL = "url";

    public static final String SCHOOL_TRENDS_TITLE = "title";

    public static final String MARK_ID = "mark_id";

    public static final String SCHOOL_TRENDS_TIME = "time";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            MARK_ID + " text, " +
            SCHOOL_TRENDS_TITLE + " text, " +
            SCHOOL_TRENDS_TIME + " text, "+
            SCHOOL_TRENDS_URL + " text)";
}
