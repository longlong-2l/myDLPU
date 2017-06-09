package com.surpassli.www.myapp.database.table;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class AccountTable {
    public static final String NAME="AccountTable";
    private static final String DATA_ID="data_id";
    public static final String USER_ID="user_id";
    public static final String USERNAME="username";
    private static final String USERPASSWORD="userPassword";
    public static final String LOGIN = "isLogin";

    public static final String CREATE_TABLE = "create table " + NAME + "(" +
            DATA_ID + " INTEGER primary key autoincrement, " +
            USER_ID + " varchar(10), " +
            USERNAME + " varchar(20), " +
            USERPASSWORD + " varchar(20) NULL, " +
            LOGIN + " varchar(2))";
}
