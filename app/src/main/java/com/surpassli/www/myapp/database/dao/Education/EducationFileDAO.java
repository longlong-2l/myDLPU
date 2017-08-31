package com.surpassli.www.myapp.database.dao.Education;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.Education.EducationFileTable;
import com.surpassli.www.myapp.database.table.home.SchoolNoticeTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Education.EducationFileModel;
import com.surpassli.www.myapp.support.htmlparse.Education.EducationFileParse;
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
 * EducationFileDAO
 */

public class EducationFileDAO implements DAO<EducationFileModel> {
    @Override
    public boolean cacheAll(List<EducationFileModel> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (EducationFileModel model : list) {
            ContentValues values = new ContentValues();
            values.put(SchoolNoticeTable.MARK_ID, System.currentTimeMillis());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_TITLE, model.getFile_title());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_URL, model.getFile_time());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_TIME, model.getFile_url());
            DataBaseHelper.insert(SchoolNoticeTable.NAME, values);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(EducationFileTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(EducationFileTable.NAME);
        final List<EducationFileModel> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            EducationFileModel model = new EducationFileModel();
            model.setFile_title(cursor.getString(cursor.getColumnIndex(EducationFileTable.EDUCATION_FILE_TITLE)));
            model.setFile_time(cursor.getString(cursor.getColumnIndex(EducationFileTable.EDUCATION_FILE_TIME)));
            model.setFile_url(cursor.getString(cursor.getColumnIndex(EducationFileTable.EDUCATION_FILE_URL)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<EducationFileModel>(EVENT.EDUCATION_FILE_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<EducationFileModel>(EVENT.EDUCATION_FILE_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.EDUCATION_FILE, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<EducationFileModel>(EVENT.EDUCATION_FILE_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                EducationFileParse notices_Parse = new EducationFileParse(res);
                final List<EducationFileModel> hot_news_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!hot_news_list.isEmpty()) {
                            cacheAll(hot_news_list);
                            EventBus.getDefault().post(new EventModel<EducationFileModel>(EVENT.EDUCATION_FILE_NET_SUCCESS, hot_news_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<EducationFileModel>(EVENT.EDUCATION_FILE_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
