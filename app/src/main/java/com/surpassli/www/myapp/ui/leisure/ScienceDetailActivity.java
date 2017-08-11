package com.surpassli.www.myapp.ui.leisure;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.ScienceModel;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.common.DisplayUtil;
import com.surpassli.www.myapp.ui.Base.BaseDetailActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/11.
 * ScienceDetailActivity
 */

public class ScienceDetailActivity extends BaseDetailActivity{

    private ScienceModel model;

    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        TCAgent.onPageStart(InitApp.AppContext, TAG);
    }

    @Override
    public void onDataRefresh() {
        HttpUtil.sendGetOkhttp(model.getUrl(), new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    @Override
    protected void onEventComing(EventModel eventModel) {
        switch (eventModel.getEventCode()) {
            case EVENT.SEND_MODEL_DETAIL:
                model = (ScienceModel) eventModel.getData();
                initView();
                break;
            case EVENT.SCIENCE_DETAILS_REFRESH_SUCCESS:
                scrollView.setVisibility(View.VISIBLE);
                scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        topImage.setTranslationY(Math.max(-scrollY / 2, -DisplayUtil.dip2px(getBaseContext(), 170)));
                    }
                });
//                contentView.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"guokr.css\" />" + model.getScienceDetails(), "text/html", "utf-8", null);
                setMainContentBg(model.getImage_info().getUrl());
                hideLoading();
                break;
            case EVENT.SCIENCE_DETAILS_REFRESH_FAILURE:
                hideLoading();
                displayNetworkError();
                break;
        }
    }

    @Override
    public void initView() {
        super.initView();
        contentView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                onEventMainThread(new EventModel<ScienceModel>(EVENT.SCIENCE_DETAILS_REFRESH_FAILURE));
            }
        });
    }
}
