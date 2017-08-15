package com.surpassli.www.myapp.ui.leisure;

import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.utils.common.DisplayUtil;
import com.surpassli.www.myapp.ui.Base.BaseDetailActivity;

public class FilmDetailActivity extends BaseDetailActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDataRefresh() {

    }

    @Override
    public void initView() {
        super.initView();
        contentView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return true;
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {

            }
        });
    }

    @Override
    protected void onEventComing(EventModel eventModel) {
        switch (eventModel.getEventCode()){
            case EVENT.SEND_MODEL_DETAIL:
                FilmModel filmModel = (FilmModel) eventModel.getData();
                initView();
                scrollView.setVisibility(View.VISIBLE);
                scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                        topImage.setTranslationY(Math.max(-scrollY / 2, -DisplayUtil.dip2px(getBaseContext(), 170)));
                    }
                });
                contentView.loadUrl(filmModel.getUrl());
                setMainContentBg(filmModel.getTopPic());
                hideLoading();
                break;
            case EVENT.FILM_DETAILS_REFRESH_FAILURE:
                hideLoading();
                break;
        }
    }
}
