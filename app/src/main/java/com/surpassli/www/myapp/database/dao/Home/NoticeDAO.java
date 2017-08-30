package com.surpassli.www.myapp.database.dao.Home;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.home.SchoolNoticeTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Notice_Model;
import com.surpassli.www.myapp.support.htmlparse.home.NoticeParse;
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

public class NoticeDAO implements DAO<Notice_Model> {
    @Override
    public boolean cacheAll(List<Notice_Model> list) {
        if (list == null) {
            return false;
        }
        clearCache();
        for (Notice_Model model : list) {
            ContentValues values = new ContentValues();
            values.put(SchoolNoticeTable.MARK_ID, System.currentTimeMillis());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_TITLE, model.getNotice_title());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_URL, model.getNotice_url());
            values.put(SchoolNoticeTable.SCHOOL_NOTICE_TIME, model.getNotice_time());
            DataBaseHelper.insert(SchoolNoticeTable.NAME, values);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(SchoolNoticeTable.NAME);
        return true;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());
        Cursor cursor = DataBaseHelper.selectAll(SchoolNoticeTable.NAME);
        final List<Notice_Model> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Notice_Model model = new Notice_Model();
            model.setNotice_title(cursor.getString(cursor.getColumnIndex(SchoolNoticeTable.SCHOOL_NOTICE_TITLE)));
            model.setNotice_url(cursor.getString(cursor.getColumnIndex(SchoolNoticeTable.SCHOOL_NOTICE_URL)));
            model.setNotice_time(cursor.getString(cursor.getColumnIndex(SchoolNoticeTable.SCHOOL_NOTICE_TIME)));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    EventBus.getDefault().post(new EventModel<Notice_Model>(EVENT.SCHOOL_NOTICES_CACHE_SUCCESS, list));
                } else {
                    EventBus.getDefault().post(new EventModel<Notice_Model>(EVENT.SCHOOL_NOTICES_CACHE_FAIL));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.SCHOOL_NOTICE, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<Notice_Model>(EVENT.SCHOOL_NOTICES_NET_FAIL));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                byte[] b = response.body().bytes();
                String res = new String(b, "GB2312");
                NoticeParse notices_Parse = new NoticeParse(res);
                final List<Notice_Model> notices_list = notices_Parse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (!notices_list.isEmpty()) {
                            cacheAll(notices_list);
                            EventBus.getDefault().post(new EventModel<Notice_Model>(EVENT.SCHOOL_NOTICES_NET_SUCCESS, notices_list));
                        } else {
                            EventBus.getDefault().post(new EventModel<Notice_Model>(EVENT.SCHOOL_NEWS_NET_FAIL));
                        }
                    }
                });
            }
        });
    }
}
