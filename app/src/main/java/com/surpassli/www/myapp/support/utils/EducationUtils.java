package com.surpassli.www.myapp.support.utils;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.surpassli.www.myapp.model.News.Notice_Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by SurpassLi on 2017/2/8.
 */
public class EducationUtils {

    public static ArrayList<Notice_Model> handNew_Notice(String response) {
        if (!TextUtils.isEmpty(response)) {
            ArrayList<Notice_Model> arrayList = new ArrayList<Notice_Model>();
            try {
                JSONObject jsonObject = new JSONObject(response);
                if ("Success".equals(jsonObject.getString("message"))) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Gson gson = new Gson();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String jsonObject1 = jsonArray.getJSONObject(i).toString();
                        Notice_Model Notice_Model = new Gson().fromJson(jsonObject1, Notice_Model.class);
                        arrayList.add(Notice_Model);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return arrayList;
        }
        return null;
    }
}
