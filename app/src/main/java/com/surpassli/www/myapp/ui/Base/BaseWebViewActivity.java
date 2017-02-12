package com.surpassli.www.myapp.ui.Base;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/2/11.
 */
public abstract class BaseWebViewActivity extends BaseToolBarActivity{
    protected WebView wv_base;
    protected ProgressBar progressBar;
    protected boolean isLoading = true;
    protected String title="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basewebview);
        initData();
        initToolBar();
        setToolbarTitle("应用简介");
    }

    protected abstract String getLink();

    private void initData() {
        wv_base = (WebView) findViewById(R.id.wv_base);
        progressBar = (ProgressBar) findViewById(R.id.pb_basewebview);
        wv_base.getSettings().setJavaScriptEnabled(true);
        //覆盖启动默认浏览器的行为
        wv_base.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageFinished(view ,url);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        //管理加载进度的方法
        wv_base.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(isLoading){
                     progressBar.incrementProgressBy(newProgress-progressBar.getProgress());
                    if(newProgress > 80){
                        isLoading = false;
                        progressBar.setVisibility(View.GONE);
                    }
                }
            }
        });

        if(!TextUtils.isEmpty(getLink())){
            wv_base.loadUrl(getLink());
        }
    }
}
