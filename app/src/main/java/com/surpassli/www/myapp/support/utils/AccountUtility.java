package com.surpassli.www.myapp.support.utils;

import android.content.Context;

import com.surpassli.www.myapp.database.dao.ExerciseDAO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/2/20.
 */
public class AccountUtility {

    public static boolean handExerciseYard(String response,Context context) {
        ExerciseDAO exerciseDAO = new ExerciseDAO(context);

        try {
            JSONObject jsonObject = new JSONObject(response);
            String code = jsonObject.getString("message");
            if (code.equals("数据获取成功")){
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                    exerciseDAO.insert(jsonObject1.getString("name"),jsonObject1.getString("content"),"empty");
                }
                return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
