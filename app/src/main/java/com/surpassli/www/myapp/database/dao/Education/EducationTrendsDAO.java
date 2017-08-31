package com.surpassli.www.myapp.database.dao.Education;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.Education.EducationTrendsTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationTrendsModel;
import com.surpassli.www.myapp.support.htmlparse.Education.EducationTrendsParse;
import com.surpassli.www.myapp.support.htmlparse.home.HotNewsParse;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationTrendsDAO
 */

public class EducationTrendsDAO implements DAO<EducationTrendsModel>{
    @Override
    public boolean cacheAll(List<EducationTrendsModel> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (EducationTrendsModel model : list) {
            ContentValues values = new ContentValues();
            values.put(EducationTrendsTable.MARK_ID, System.currentTimeMillis());
            values.put(EducationTrendsTable.EDUCATION_TRENDS_TITLE, model.getTrends_title());
            values.put(EducationTrendsTable.EDUCATION_TRENDS_URL, model.getTrends_url());
            values.put(EducationTrendsTable.EDUCATION_TRENDS_TIME, model.getTrends_time());
            DataBaseHelper.insert(EducationTrendsTable.NAME, values);
        }
        return false;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(EducationTrendsTable.NAME);
        return false;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(EducationTrendsTable.NAME);
        final List<EducationTrendsModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            EducationTrendsModel model = new EducationTrendsModel();
            model.setTrends_title(cursor.getString(cursor.getColumnIndex(EducationTrendsTable.EDUCATION_TRENDS_TITLE)));
            model.setTrends_time(cursor.getString(cursor.getColumnIndex(EducationTrendsTable.EDUCATION_TRENDS_TIME)));
            model.setTrends_url(cursor.getString(cursor.getColumnIndex(EducationTrendsTable.EDUCATION_TRENDS_URL)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<EducationTrendsModel>(EVENT.EDUCATION_TRENDS_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<EducationTrendsModel>(EVENT.EDUCATION_TRENDS_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.EDUCATION_NEWS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<EducationTrendsModel>(EVENT.EDUCATION_TRENDS_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                EducationTrendsParse notices_Parse = new EducationTrendsParse(res);
                final List<EducationTrendsModel> education_trends_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!education_trends_list.isEmpty()) {
                            cacheAll(education_trends_list);
                            EventBus.getDefault().post(new EventModel<EducationTrendsModel>(EVENT.EDUCATION_TRENDS_NET_SUCCESS, education_trends_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<EducationTrendsModel>(EVENT.EDUCATION_TRENDS_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
