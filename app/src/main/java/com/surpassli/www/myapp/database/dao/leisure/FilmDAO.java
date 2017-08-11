package com.surpassli.www.myapp.database.dao.leisure;

import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.database.DataBaseHelper;
import com.surpassli.www.myapp.database.dao.DAO;
import com.surpassli.www.myapp.database.table.leisure.FilmTable;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/11.
 * FilmDAO
 */

public class FilmDAO implements DAO<FilmDAO> {
    @Override
    public boolean cacheAll(ArrayList<FilmDAO> list) {
        return false;
    }

    @Override
    public boolean clearCache() {
        DataBaseHelper.clearTable(FilmTable.NAME);
        return false;
    }

    @Override
    public void loadFromCache() {

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

            }
        });
    }
}
