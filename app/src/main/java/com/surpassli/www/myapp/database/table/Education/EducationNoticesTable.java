package com.surpassli.www.myapp.database.table.Education;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationNoticesTable
 */

public class EducationNoticesTable {
    public static final String NAME = "EducationNoticesTable";

    public static final String EDUCATION_NOTICES_TITLE = "title";

    public static final String EDUCATION_NOTICES_TIME = "time";

    public static final String EDUCATION_NOTICES_URL = "url";

    public static final String MARK_ID = "mark_id";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            EDUCATION_NOTICES_TITLE + " text, " +
            EDUCATION_NOTICES_URL + " text, " +
            EDUCATION_NOTICES_TIME + " text, " +
            MARK_ID + " text)";
}
