package com.surpassli.www.myapp.database.dao.Home;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.home.SchoolTrendsTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Trends_Model;
import com.surpassli.www.myapp.support.htmlparse.home.TrendsParse;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeDAO
 */

public class TrendsDAO implements DAO<Trends_Model> {
    @Override
    public boolean cacheAll(List<Trends_Model> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (Trends_Model model : list) {
            ContentValues values = new ContentValues();
            values.put(SchoolTrendsTable.MARK_ID, System.currentTimeMillis());
            values.put(SchoolTrendsTable.SCHOOL_TRENDS_TITLE, model.getTrends_title());
            values.put(SchoolTrendsTable.SCHOOL_TRENDS_URL, model.getTrends_url());
            values.put(SchoolTrendsTable.SCHOOL_TRENDS_TIME, model.getTrends_time());
            DataBaseHelper.insert(SchoolTrendsTable.NAME, values);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(SchoolTrendsTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(SchoolTrendsTable.NAME);
        final List<Trends_Model> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Trends_Model model = new Trends_Model();
            model.setTrends_title(cursor.getString(cursor.getColumnIndex(SchoolTrendsTable.SCHOOL_TRENDS_TITLE)));
            model.setTrends_url(cursor.getString(cursor.getColumnIndex(SchoolTrendsTable.SCHOOL_TRENDS_URL)));
            model.setTrends_time(cursor.getString(cursor.getColumnIndex(SchoolTrendsTable.SCHOOL_TRENDS_TIME)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_TRENDS_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_TRENDS_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.SCHOOL_NEWS_STATUS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_TRENDS_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                TrendsParse notices_Parse = new TrendsParse(res);
                final List<Trends_Model> trends_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!trends_list.isEmpty()) {
                            cacheAll(trends_list);
                            EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_TRENDS_NET_SUCCESS, trends_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_TRENDS_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
