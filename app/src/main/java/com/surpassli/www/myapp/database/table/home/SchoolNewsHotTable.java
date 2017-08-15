package com.surpassli.www.myapp.database.table.home;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNewsHotTable 校内头条新闻
 */

public class SchoolNewsHotTable {

    public static final String NAME = "SchoolNewsHotTable";

    public static final String SCHOOL_NEWS_HOT_URL = "url";

    public static final String SCHOOL_NEWS_HOT_TITLE = "title";

    public static final String MARK_ID = "mark_id";

    public static final String SCHOOL_NEWS_HOT_TIME = "time";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            MARK_ID + " text, " +
            SCHOOL_NEWS_HOT_TITLE + " text, " +
            SCHOOL_NEWS_HOT_TIME + " text, "+
            SCHOOL_NEWS_HOT_URL + " text)";
}
