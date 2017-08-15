package com.surpassli.www.myapp.database.dao.leisure;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.leisure.FilmTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.htmlparse.leisure.jianshuListParse;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/11.
 * FilmDAO
 */

public class FilmDAO implements DAO<FilmModel> {
    @Override
    public boolean cacheAll(List<FilmModel> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        clearCache();
        for (FilmModel filmModel : list) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(FilmTable.URL, filmModel.getUrl());
            contentValues.put(FilmTable.TITLE, filmModel.getTitle());
            contentValues.put(FilmTable.TOP_PIC, filmModel.getTopPic());
            contentValues.put(FilmTable.DETAIL, filmModel.getDetail());
            contentValues.put(FilmTable.READING_COUNT, filmModel.getReadingCount());
            DataBaseHelper.insert(FilmTable.NAME, contentValues);
        }
        return true;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(FilmTable.NAME);
        return false;
    }

    @Override
    public void loadFromCache() {
        Handler handler = new Handler(Looper.getMainLooper());

        Cursor cursor = DataBaseHelper.selectAll(FilmTable.NAME);

        final List<FilmModel> list = new ArrayList<>();

        while (cursor.moveToNext()) {
            FilmModel model = new FilmModel();
            model.setUrl(cursor.getString(FilmTable.ID_URL));
            model.setTitle(cursor.getString(FilmTable.ID_TITLE));
            model.setReadingCount(cursor.getString(FilmTable.ID_READING_COUNT));
            model.setDetail(cursor.getString(FilmTable.ID_DETAIL));
            model.setTopPic(cursor.getString(FilmTable.ID_TOP_PIC));
            list.add(model);
        }

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (!list.isEmpty()) {
                    // 从缓存获取成功　发送消息
                    EventBus.getDefault().post(new EventModel<FilmModel>(EVENT.FILM_LOAD_CACHE_SUCCESS, list));
                } else {
                    // 从缓存获取失败
                    EventBus.getDefault().post(new EventModel<FilmModel>(EVENT.FILM_LOAD_CACHE_FAILURE));
                }
            }
        });
    }

    @Override
    public void loadFromNet() {
        final Handler handler = new Handler(Looper.getMainLooper());
        HttpUtil.sendGetOkhttp(AppApi.JianShu_List_URL, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<FilmModel>(EVENT.SCIENCE_REFRESH_FAILURE));
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                jianshuListParse myParse = new jianshuListParse(response.body().string());
                final List<FilmModel> list = myParse.getEndList();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (list != null) {
                            cacheAll(list);
                            EventBus.getDefault().post(new EventModel<FilmModel>(EVENT.FILM_REFRESH_SUCCESS, list));
                        } else {
                            EventBus.getDefault().post(new EventModel<FilmModel>(EVENT.FILM_REFRESH_FAILURE));
                        }
                    }
                });
            }
        });
    }
}
