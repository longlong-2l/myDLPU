package com.surpassli.www.myapp.ui.Base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.view.base.BaseView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by SurpassLi on 2017/2/11.
 * Description: BaseWebViewActivity
 */
public abstract class BaseWebViewActivity extends BaseToolBarActivity implements BaseView {
    protected WebView wv_base;
    protected ProgressBar progressBar;
    protected boolean isLoading = true;
    protected String title;
    protected CardView cv_cardView;
    protected String styleView;
    public static final String VIEW_STYLE = "viewStyle";
    public static final String ALL_VIEW = "allView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basewebview);
        initToolBar();
        initView();
        setToolbarTitle(title);
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public abstract void onEventComing(EventModel eventModel);  //EventBus绑定

    @Override
    public void hideLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void displayLoading() {
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void displayNetworkError() {
    }

    @Override
    public void initView() {
        wv_base = (WebView) findViewById(R.id.wv_base);
        progressBar = (ProgressBar) findViewById(R.id.pb_basewebview);
        cv_cardView = (CardView) findViewById(R.id.cv_web);
        if (ALL_VIEW.equals(styleView)) {
            FrameLayout.LayoutParams cardParams = (FrameLayout.LayoutParams) cv_cardView.getLayoutParams();
            cardParams.leftMargin = 0;
            cardParams.rightMargin = 0;
            cv_cardView.setLayoutParams(cardParams);
            FrameLayout.LayoutParams webParams = (FrameLayout.LayoutParams) wv_base.getLayoutParams();
            webParams.leftMargin = 0;
            webParams.rightMargin = 0;
            wv_base.setLayoutParams(webParams);
        }
        WebSettings webSettings = wv_base.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //覆盖启动默认浏览器的行为
        wv_base.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }
        });
        //管理加载进度的方法
        wv_base.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (isLoading) {
                    progressBar.incrementProgressBy(newProgress - progressBar.getProgress());
                    if (newProgress >= 50) {
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
