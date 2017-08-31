package com.surpassli.www.myapp.database.dao.Education;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.Education.EducationNoticesTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationNoticesModel;
import com.surpassli.www.myapp.support.htmlparse.Education.EducationNoticesParse;
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
 * EducationNoticesDAO
 */

public class EducationNoticesDAO implements DAO<EducationNoticesModel> {
    @Override
    public boolean cacheAll(List<EducationNoticesModel> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (EducationNoticesModel model : list) {
            ContentValues values = new ContentValues();
            values.put(EducationNoticesTable.MARK_ID, System.currentTimeMillis());
            values.put(EducationNoticesTable.EDUCATION_NOTICES_TITLE, model.getNotices_title());
            values.put(EducationNoticesTable.EDUCATION_NOTICES_TIME, model.getNotices_time());
            values.put(EducationNoticesTable.EDUCATION_NOTICES_URL, model.getNotices_url());
            DataBaseHelper.insert(EducationNoticesTable.NAME, values);
        }
        return false;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(EducationNoticesTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(EducationNoticesTable.NAME);
        final List<EducationNoticesModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            EducationNoticesModel model = new EducationNoticesModel();
            model.setNotices_title(cursor.getString(cursor.getColumnIndex(EducationNoticesTable.EDUCATION_NOTICES_TITLE)));
            model.setNotices_time(cursor.getString(cursor.getColumnIndex(EducationNoticesTable.EDUCATION_NOTICES_TIME)));
            model.setNotices_url(cursor.getString(cursor.getColumnIndex(EducationNoticesTable.EDUCATION_NOTICES_URL)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<EducationNoticesModel>(EVENT.EDUCATION_NOTICES_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<EducationNoticesModel>(EVENT.EDUCATION_NOTICES_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.EDUCATION_NOTICE, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<EducationNoticesModel>(EVENT.EDUCATION_NOTICES_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                EducationNoticesParse notices_Parse = new EducationNoticesParse(res);
                final List<EducationNoticesModel> hot_news_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!hot_news_list.isEmpty()) {
                            cacheAll(hot_news_list);
                            EventBus.getDefault().post(new EventModel<EducationNoticesModel>(EVENT.EDUCATION_NOTICES_NET_SUCCESS, hot_news_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<EducationNoticesModel>(EVENT.EDUCATION_NOTICES_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
