package com.surpassli.www.myapp.ui.Base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    protected String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basewebview);
        initToolBar();
        setToolbarTitle(title);
        initView();
        EventBus.getDefault().register(this);
    }
    @Subscribe
    public abstract void onEventComing(EventModel eventModel);  //EventBus绑定


    @Override
    public void hideLoading() {
        if (progressBar!=null) {
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
    public void displayNetworkError() { }

    @Override
    public void initView() {
        wv_base = (WebView) findViewById(R.id.wv_base);
        progressBar = (ProgressBar) findViewById(R.id.pb_basewebview);
        WebSettings webSettings = wv_base.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (!getApplication().getString(R.string.app_introduce).equals(title) && !getApplication().getString(R.string.license).equals(title)) {
            webSettings.setUseWideViewPort(true);
            webSettings.setLoadWithOverviewMode(true);
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int mDensity = metrics.densityDpi;
            if (mDensity == 240) {
                webSettings.setUseWideViewPort(true);
            } else if (mDensity == 160) {
                webSettings.setUseWideViewPort(true);
            } else if (mDensity == 120) {
                webSettings.setUseWideViewPort(true);
            } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
                webSettings.setUseWideViewPort(true);
            } else if (mDensity == DisplayMetrics.DENSITY_TV) {
                webSettings.setUseWideViewPort(true);
            }
        }
        //覆盖启动默认浏览器的行为
        wv_base.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }
        });
        //管理加载进度的方法
        wv_base.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (isLoading) {
                    progressBar.incrementProgressBy(newProgress - progressBar.getProgress());
                    if (newProgress > 99) {
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });
//        wv_base.loadDataWithBaseURL("file:///android_asset/", "<link rel=\"stylesheet\" type=\"text/css\" href=\"guokr.css\" />" + getLink(), "text/html", "utf-8", null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
