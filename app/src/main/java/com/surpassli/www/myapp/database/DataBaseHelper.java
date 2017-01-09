package com.surpassli.www.myapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.surpassli.www.myapp.database.table.AccountTable;

/**
 * Created by dell on 2017/1/9.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyApp";
    private static final int DB_VERSION = 2;//数据库版本号
    private static SQLiteDatabase db = null;

    public DataBaseHelper(Context context) {
        super(context, "MyApp.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户
        db.execSQL(AccountTable.CREATE_TABLE);

    }

    //修改数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
