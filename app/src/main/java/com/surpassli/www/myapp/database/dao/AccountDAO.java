package com.surpassli.www.myapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.table.AccountTable;

/**
 * Created by SurpassLi on 2017/1/9.
 */
public class AccountDAO {
    public static final String TAG = "AccountDAO";
    private Context context;
    private DataBaseHelper mDataBaseHelper;

    public AccountDAO(Context context) {
        super();
        this.context = context;
        mDataBaseHelper = new DataBaseHelper(context,"MyApp.db",null,2);
    }

    //插入一个用户
    public void insert_user(String userId, String isLogin,String userName){
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        if (database.isOpen()){
            ContentValues contentValues = new ContentValues();
            contentValues.put(AccountTable.USER_ID,userId);
            contentValues.put(AccountTable.LOGIN,userId);
            contentValues.put(AccountTable.USERNAME,userId);
            database.insert(AccountTable.NAME,null,contentValues);
            database.close();
        }
    }

    //删除所有用户
    public void delete_user(int userId){
        SQLiteDatabase database = mDataBaseHelper.getWritableDatabase();
        if (database.isOpen()){
            database.delete(AccountTable.NAME,"user_id = "+String.valueOf(userId),null);
//            database.execSQL("delete from AccountTable");
            database.close();
        }
    }
}
