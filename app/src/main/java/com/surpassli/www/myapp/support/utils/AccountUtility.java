package com.surpassli.www.myapp.support.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.surpassli.www.myapp.database.dao.ExerciseDAO;
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

    public static ExerciseDAO handExerciseYard(String response,Context context) {
        mExerciseYardList = new ArrayList<ExerciseYard>();
        ExerciseYard mExerciseYard;
        ExerciseDAO exerciseDAO = new ExerciseDAO(context);

        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.getString("message");
            if (code.equals("数据获取成功")){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    mExerciseYard =  new ExerciseYard();
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                    mExerciseYard.setTag(jsonObject1.getString("name"));
                    exerciseDAO.insert(jsonObject1.getString("name"),"aa","aa");
                    mExerciseYardList.add(mExerciseYard);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return exerciseDAO;
    }

    ;
}
