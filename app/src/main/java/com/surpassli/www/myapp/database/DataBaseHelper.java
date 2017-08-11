package com.surpassli.www.myapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.database.table.AccountTable;
import com.surpassli.www.myapp.database.table.ExerciseYardTable;
import com.surpassli.www.myapp.database.table.leisure.ScienceTable;

/**
 * Created by SurpassLi on 2017/1/9.
 * DataBaseHelper
 */
public class DataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "MyApp";
    private static final int DB_VERSION = 2;//数据库版本号
    private static final String CLEAR_TABLE_DATA = "delete from ";
    private static SQLiteDatabase db = null;
    private static DataBaseHelper instance = null;

//    public DataBaseHelper(Context context) {
//        super(context, "MyApp.db", null, 1);
//    }

    public DataBaseHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int dbVersion) {
        super(context, dbName, factory, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //用户
        db.execSQL(AccountTable.CREATE_TABLE);

        /*
         * Leisure
         */
//        db.execSQL(DailyTable.CREATE_TABLE);
//        db.execSQL(FilmTable.CREATE_TABLE);
        db.execSQL(ScienceTable.CREATE_TABLE);

        //活动场所
        db.execSQL(ExerciseYardTable.CREATE_TABLE);

        //景点名称
    }

    //修改数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion == 1 && newVersion == 2) {
//            db.execSQL(DROP_TABLE+NotifyTable.NAME);
//            db.execSQL(NotifyTable.CREATE_TABLE);
//            db.execSQL(DROP_TABLE+WeatherInfoTable.NAME);
//            db.execSQL(WeatherInfoTable.CREATE_TABLE);
//            db.execSQL(DROP_TABLE+CourseTable.NAME);
//            db.execSQL(CourseTable.CREATE_TABLE);
        }
    }

    public static synchronized DataBaseHelper getInstance() {
        if (instance == null) {
            instance = new DataBaseHelper(InitApp.getContext(), DB_NAME, null, DB_VERSION);
        }
        return instance;
    }

    public static synchronized SQLiteDatabase getDatabase() {
        if (db == null) {
            db = getInstance().getWritableDatabase();
        }
        return db;
    }

    public static void exeSQL(String sql, String... params) {
        getDatabase().execSQL(sql, params);
    }

    public static void exeSQL(String sql) {
        getDatabase().execSQL(sql);
    }

    public static void insert(String table, ContentValues cv) {
        getDatabase().insert(table, null, cv);
    }

    public static Cursor selectAll(String table) {
        return getDatabase().query(table, null, null, null, null, null, null);
    }

    public static void clearTable(String table) {
        exeSQL(CLEAR_TABLE_DATA + table);
    }

    public static void clearUserData() {
//        clearTable(CourseTable.NAME);
//        clearTable(CourseInfoTable.NAME);
//        clearTable(CourseScoreTable.NAME);
//        clearTable(ExamTimeTable.NAME);
    }

    public static void clearLibraryData() {
//        clearTable(BookBorrowedTable.NAME);
    }
}
