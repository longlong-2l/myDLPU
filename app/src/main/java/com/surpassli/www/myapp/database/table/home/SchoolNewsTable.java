package com.surpassli.www.myapp.database.table.home;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNewsTable
 */

public class SchoolNewsTable {

    public static final String NAME = "SchoolNewsTable";

    public static final String SCHOOL_NEWS_URL = "url";

    public static final String SCHOOL_NEWS_TITLE = "title";

    public static final String MARK_ID = "mark_id";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            MARK_ID + " text, " +
            SCHOOL_NEWS_TITLE + " text, " +
            SCHOOL_NEWS_URL + " text)";

}