package com.surpassli.www.myapp.database.table.home;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNoticeTable 校内通知公告
 */

public class SchoolNoticeTable {

    public static final String NAME = "SchoolNoticeTable";

    public static final String SCHOOL_NOTICE_TITLE = "";

    public static final String SCHOOL_NOTICE_TIME = "";

    public static final String SCHOOL_NOTICE_URL = "";

    public static final String MARK_ID = "";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            SCHOOL_NOTICE_TITLE + " text, " +
            SCHOOL_NOTICE_URL + " text, " +
            SCHOOL_NOTICE_TIME + "text, " +
            MARK_ID + " text)";
}
