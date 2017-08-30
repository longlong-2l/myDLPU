package com.surpassli.www.myapp.ui.Home;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;

import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.Home.Trends_Model;
import com.surpassli.www.myapp.support.htmlparse.home.TrendsDetailParse;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/28.
 * SchoolTrendsActivity
 */

public class SchoolDetailActivity extends BaseWebViewActivity {
    private Handler handler = new Handler(Looper.getMainLooper());

    public void getDetail(String url) {
        HttpUtil.sendGetOkhttp(url, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new EventModel<Trends_Model>(EVENT.SCHOOL_DETAIL_FAIL));
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String res = new String(response.body().bytes(),"GB2312");
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(new EventModel<String>(EVENT.SCHOOL_DETAIL_SUCCESS,new TrendsDetailParse(res).parseData()));
                    }
                });
            }
        });
    }

    @Override
    @Subscribe
    public void onEventComing(EventModel eventModel) {
        switch (eventModel.getEventCode()) {
            case EVENT.SEND_MODEL_DETAIL:
//                initView();
                break;
            case EVENT.SCHOOL_DETAIL_SUCCESS:
                //指定css样式显示网页内容
                wv_base.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"detail.css\" />"+eventModel.getData().toString(), "text/html", "GB2312", null);
                hideLoading();
                break;
            case EVENT.SCHOOL_DETAIL_FAIL:

                break;
        }
    }

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        getDetail(url);
    }
}
