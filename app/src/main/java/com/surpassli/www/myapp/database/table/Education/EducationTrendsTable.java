package com.surpassli.www.myapp.database.table.Education;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationTrendsTable
 */

public class EducationTrendsTable {
    public static final String NAME = "EducationTrendsTable";

    public static final String EDUCATION_TRENDS_TITLE = "title";

    public static final String EDUCATION_TRENDS_TIME = "time";

    public static final String EDUCATION_TRENDS_URL = "url";

    public static final String MARK_ID = "mark_id";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            EDUCATION_TRENDS_TITLE + " text, " +
            EDUCATION_TRENDS_URL + " text, " +
            EDUCATION_TRENDS_TIME + " text, " +
            MARK_ID + " text)";
}
