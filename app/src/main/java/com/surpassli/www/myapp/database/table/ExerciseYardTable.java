package com.surpassli.www.myapp.database.table;

/**
 * Created by SurpassLi on 2017/2/20.
 */
public class ExerciseYardTable {
    public static final String NAME = "ExerciseYardTable";
    public static final String NAME2 = "name";
    public static final String CONTENT = "content";
    public static final String SUBJECT = "subject";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            "_id integer primary key autoincrement, " +
            NAME2 + " text, " +
            CONTENT + " text, "+
            SUBJECT + " text)";
}
