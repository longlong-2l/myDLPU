package com.surpassli.www.myapp.database;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.surpassli.www.myapp.database.table.AccountTable;
import com.surpassli.www.myapp.database.table.ExerciseYardTable;

import org.litepal.LitePalApplication;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyApp";
    private static final int DB_VERSION = 2;//数据库版本号
    private static final String CLEAR_TABLE_DATA="delete from ";
    private static SQLiteDatabase db = null;
    private static DataBaseHelper instance = null;

    public DataBaseHelper(Context context) {
        super(context, "MyApp.db", null, 1);
    }

//    public DataBaseHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int dbVersion) {
//        super(context,dbName,factory,dbVersion);
//    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户
//        db.execSQL(AccountTable.CREATE_TABLE);

        //活动场所
        db.execSQL(ExerciseYardTable.CREATE_TABLE);

        //景点名称
    }

    //修改数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public static  void clearTable(String table){
//       db.execSQL(CLEAR_TABLE_DATA+table);
//    }
//
//    public static synchronized DataBaseHelper getInstance(){
//        if (instance == null){
//               new DataBaseHelper(LitePalApplication.getContext(),DB_NAME,null,DB_VERSION);
//        }
//        return instance;
//    }
//
//    public static synchronized SQLiteDatabase getDatabase(){
//        if (db == null){
//            getInstance().getWritableDatabase();
//        }
//        return db;
//    }
//
//    public static void insert(String table, ContentValues cv){
//        getDatabase().insert(table,null,cv);
//    }
}
