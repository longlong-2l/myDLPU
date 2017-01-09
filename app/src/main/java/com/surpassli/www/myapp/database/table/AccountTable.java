package com.surpassli.www.myapp.database.table;

/**
 * Created by dell on 2017/1/9.
 */
public class AccountTable {
    private static final String NAME="AccountTable";
    private static final String USER_ID="user_id";
    private static final String USERNAME="username";
    private static final String USERPASSWORD="userpassword";
    private static boolean LOGIN = false;

    public static final String CREATE_TABLE="create table "+NAME+"("+
            USER_ID + " INTEGER primary key autoincrement, " +
            USERNAME + " varchar(20), " +
            USERPASSWORD + " varchar(10) "+
            LOGIN + "BOOLEAN)";
}
