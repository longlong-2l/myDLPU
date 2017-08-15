package com.surpassli.www.myapp.database.dao.Home;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.home.SchoolNewsTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.School_News_Model;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.htmlparse.home.SchoolNewsParse;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNewsDAO
 */

public class SchoolNewsDAO implements DAO<School_News_Model> {
    @Override
    public boolean cacheAll(List<School_News_Model> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (School_News_Model model : list) {
            ContentValues values = new ContentValues();
            values.put(SchoolNewsTable.MARK_ID, System.currentTimeMillis());
            values.put(SchoolNewsTable.SCHOOL_NEWS_TITLE, model.getSchool_news_title());
            values.put(SchoolNewsTable.SCHOOL_NEWS_URL, model.getSchool_news_url());
            values.put(SchoolNewsTable.SCHOOL_NEWS_TIME,model.getSchool_news_time());
            DataBaseHelper.insert(SchoolNewsTable.NAME, values);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(SchoolNewsTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(SchoolNewsTable.NAME);
        final List<School_News_Model> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            School_News_Model model = new School_News_Model();
            model.setSchool_news_title(cursor.getString(cursor.getColumnIndex(SchoolNewsTable.SCHOOL_NEWS_TITLE)));
            model.setSchool_news_url(cursor.getString(cursor.getColumnIndex(SchoolNewsTable.SCHOOL_NEWS_URL)));
            model.setSchool_news_time(cursor.getString(cursor.getColumnIndex(SchoolNewsTable.SCHOOL_NEWS_TIME)));
            list.add(model);
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<School_News_Model>(EVENT.SCHOOL_NEWS_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<School_News_Model>(EVENT.SCHOOL_NEWS_CACHE_FAIL));
                }
            }
        });

    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.SCHOOL_NEWS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<School_News_Model>(EVENT.SCHOOL_NEWS_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b =response.body().bytes();
                String info = new String(b,"GB2312");
                SchoolNewsParse schoolNewsParse = new SchoolNewsParse(info);
                final List<School_News_Model> school_news_list = schoolNewsParse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!school_news_list.isEmpty()) {
                            cacheAll(school_news_list);
                            EventBus.getDefault().post(new EventModel<School_News_Model>(EVENT.SCHOOL_NEWS_NET_SUCCESS,school_news_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<School_News_Model>(EVENT.SCHOOL_NEWS_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
