package com.surpassli.www.myapp.support.utils;

import android.database.sqlite.SQLiteDatabase;

import com.surpassli.www.myapp.model.ExerciseYard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/2/20.
 */
public class AccountUtility {

    private static ArrayList<ExerciseYard> mExerciseYardList;

    public static ArrayList<ExerciseYard> handExerciseYard(String response) {
        mExerciseYardList = new ArrayList<ExerciseYard>();
        ExerciseYard mExerciseYard =  new ExerciseYard();
        try {
//            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase("exercise_db.db",MODE_PRIVATE,null);
            JSONObject jsonObject = new JSONObject(response);
            int code = jsonObject.getInt("code");
            if (code == 200){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i <jsonArray.length() ; i++) {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mExerciseYardList;
    }

    ;
}
