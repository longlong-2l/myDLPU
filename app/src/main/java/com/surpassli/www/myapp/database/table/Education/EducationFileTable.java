package com.surpassli.www.myapp.database.table.Education;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationFileTable
 */

public class EducationFileTable {
    public static final String NAME = "EducationFileTable";

    public static final String EDUCATION_FILE_TITLE = "title";

    public static final String EDUCATION_FILE_TIME = "time";

    public static final String EDUCATION_FILE_URL = "url";

    public static final String MARK_ID = "mark_id";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            EDUCATION_FILE_TITLE + " text, " +
            EDUCATION_FILE_URL + " text, " +
            EDUCATION_FILE_TIME + " text, " +
            MARK_ID + " text)";
}
