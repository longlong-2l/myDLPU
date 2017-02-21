package com.surpassli.www.myapp.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.table.ExerciseYardTable;
import com.surpassli.www.myapp.model.ExerciseYard;
import com.surpassli.www.myapp.model.ExerciseYardModel.ExerciseModel;

import java.util.ArrayList;
import java.util.List;

import static com.surpassli.www.myapp.AppVariables.username;

/**
 * Created by SurpassLi on 2017/2/20.
 */

public class ExerciseDAO {
    private Context context;
    private DataBaseHelper mDataBaseHelper;

    public ExerciseDAO(Context context) {
        super();
        this.context = context;
        mDataBaseHelper = new DataBaseHelper(context);
    }

    /**
     * 插入数据
     * @param name
     * @param content
     * @param subject
     */
    public void insert(String name,String content,String subject) {
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            ContentValues values = new ContentValues();
            values.put(ExerciseYardTable.NAME2, name);
            values.put(ExerciseYardTable.CONTENT, content);
            values.put(ExerciseYardTable.SUBJECT, subject);
            db.insert(ExerciseYardTable.NAME, null, values);
            db.close();
        }
    }

    /**
     * 查询所有数据
     * @return
     */
    public ArrayList<ExerciseYard> select() {
        ArrayList<ExerciseYard> exerciseYardsList = null;
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query("ExerciseYardTable", null, null, null, null, null, null);
            exerciseYardsList = new ArrayList<ExerciseYard>();
            while (cursor.moveToNext()) {
                ExerciseYard exerciseYard = new ExerciseYard();
                String name = cursor.getString(cursor.getColumnIndex("name"));
                exerciseYard.setTag(name);

                String content = cursor.getString(cursor.getColumnIndex("content"));
                exerciseYard.setContent(content);

                String subject = cursor.getString(cursor.getColumnIndex("subject"));
                exerciseYard.setSubject(subject);

                exerciseYardsList.add(exerciseYard);
            }
            cursor.close();
            db.close();
        }
        return exerciseYardsList;
    }

    /**
     * 查询name
     * @return
     */
    public ArrayList<ExerciseYard> selectName() {
        ArrayList<ExerciseYard> exerciseYardsList = null;
        SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.query("ExerciseYardTable", null, null, null, null, null, null);
            exerciseYardsList = new ArrayList<ExerciseYard>();
            while (cursor.moveToNext()) {
                ExerciseYard exerciseYard = new ExerciseYard();
                String name = cursor.getString(cursor.getColumnIndex("name"));
                exerciseYard.setTag(name);
                exerciseYardsList.add(exerciseYard);
            }
            cursor.close();
            db.close();
        }
        return exerciseYardsList;
    }
}
//public class ExerciseDAO implements DAO<ExerciseModel>{
//    @Override
//    public boolean cacheall(ArrayList<ExerciseModel> list) {
//        if (list == null || list.size() == 0) {
//            return false;
//        }
//        clearCache();
//
//        for (int i = 0; i < list.size(); i++) {
//            ExerciseModel exerciseModel = list.get(i);
//            ContentValues values = new ContentValues();
//            values.put(ExerciseYardTable.NAME2, exerciseModel.getName());
//            values.put(ExerciseYardTable.CONTENT, exerciseModel.getContent());
//            values.put(ExerciseYardTable.SUBJECT, exerciseModel.getSubject());
//            DataBaseHelper.insert(ExerciseYardTable.NAME, values);
//        }
//        return true;
//    }
//
//    @Override
//    public boolean clearCache() {
//        DataBaseHelper.clearTable(ExerciseYardTable.NAME);
//        return true;
//    }
//
//    @Override
//    public void loadFromCache() {
//
//    }
//
//    @Override
//    public void loadFromNet() {
//
//    }
//}
