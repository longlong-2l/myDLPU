package com.surpassli.www.myapp.database.dao.Home;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.home.SchoolNewsHotTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Hot_News_Model;
import com.surpassli.www.myapp.support.htmlparse.home.HotNewsParse;
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

public class HotNewsDAO implements DAO<Hot_News_Model> {
    @Override
    public boolean cacheAll(List<Hot_News_Model> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (Hot_News_Model model : list) {
            ContentValues values = new ContentValues();
            values.put(SchoolNewsHotTable.MARK_ID, System.currentTimeMillis());
            values.put(SchoolNewsHotTable.SCHOOL_NEWS_HOT_TITLE, model.getHot_News_title());
            values.put(SchoolNewsHotTable.SCHOOL_NEWS_HOT_URL, model.getHot_News_url());
            values.put(SchoolNewsHotTable.SCHOOL_NEWS_HOT_TIME, model.getHot_News_time());
            DataBaseHelper.insert(SchoolNewsHotTable.NAME, values);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(SchoolNewsHotTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(SchoolNewsHotTable.NAME);
        final List<Hot_News_Model> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Hot_News_Model model = new Hot_News_Model();
            model.setHot_News_title(cursor.getString(cursor.getColumnIndex(SchoolNewsHotTable.SCHOOL_NEWS_HOT_TITLE)));
            model.setHot_News_time(cursor.getString(cursor.getColumnIndex(SchoolNewsHotTable.SCHOOL_NEWS_HOT_TIME)));
            model.setHot_News_url(cursor.getString(cursor.getColumnIndex(SchoolNewsHotTable.SCHOOL_NEWS_HOT_URL)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<Hot_News_Model>(EVENT.SCHOOL_HOT_NEWS_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<Hot_News_Model>(EVENT.SCHOOL_HOT_NEWS_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.SCHOOL_NEWS_HOT, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<Hot_News_Model>(EVENT.SCHOOL_HOT_NEWS_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                HotNewsParse notices_Parse = new HotNewsParse(res);
                final List<Hot_News_Model> hot_news_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!hot_news_list.isEmpty()) {
                            cacheAll(hot_news_list);
                            EventBus.getDefault().post(new EventModel<Hot_News_Model>(EVENT.SCHOOL_HOT_NEWS_NET_SUCCESS, hot_news_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<Hot_News_Model>(EVENT.SCHOOL_HOT_NEWS_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
