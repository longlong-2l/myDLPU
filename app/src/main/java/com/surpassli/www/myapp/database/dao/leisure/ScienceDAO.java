package com.surpassli.www.myapp.database.dao.leisure;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.leisure.ScienceTable;
import com.surpassli.www.myapp.model.leisure.ScienceModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceDAO
 */

public class ScienceDAO implements DAO<ScienceModel>{

    @Override
    public boolean cacheAll(ArrayList<ScienceModel> list) {
        if (list == null || list.isEmpty()){
            return false;
        }

        clearCache();

        for (int i=0 ;i<list.size() ; i++){
            ScienceModel scienceModel = list.get(i);
            ContentValues values = new ContentValues();
            values.put(ScienceTable.REPLIES_COUNT,scienceModel.getReplies_count());
            values.put(ScienceTable.IMAGE_URL,scienceModel.getImage_info().getUrl());
            values.put(ScienceTable.URL,scienceModel.getUrl());
            values.put(ScienceTable.TITLE,scienceModel.getTitle());
//            values.put(ScienceTable.SCIENCE_DETAILS,scienceModel.getScienceDetails());
            DataBaseHelper.insert(ScienceTable.NAME,values);
        }
        return false;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(ScienceTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        final Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(ScienceTable.NAME);

        final List<ScienceModel> list = new ArrayList<>();

        while (cursor.moveToNext()){
            ScienceModel model = new ScienceModel();
            model.setReplies_count(cursor.getInt(ScienceTable.ID_REPLIES_COUNT));
            model.getImage_info().setUrl(cursor.getString(ScienceTable.ID_IMAGE_URL));
            model.setUrl(cursor.getString(ScienceTable.ID_URL));
            model.setTitle(cursor.getString(ScienceTable.ID_TITLE));
//            model.setScienceDetails(cursor.getString(ScienceTable.ID_SCIENCE_DETAILS));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()){
                    // 从缓存获取成功　发送消息
//                    EventBus.getDefault().post(new EventModel<ScienceModel>(EVENT.SCIENCE_LOAD_CACHE_SUCCESS,list));
                }else {
                    // 从缓存获取失败
//                    EventBus.getDefault().post(new EventModel<ScienceModel>(EVENT.SCIENCE_LOAD_CACHE_FAILURE));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());

    }
}
