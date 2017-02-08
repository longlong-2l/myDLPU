package com.surpassli.www.myapp.ui.Education;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/2/8.
 */
public class NewsWebView extends AppCompatActivity{
    private WebView news_WebView;
    private ProgressBar news_progressbar;
    private boolean isLoading = true;
    private String url;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newswebview);
        news_WebView = (WebView) findViewById(R.id.wv_news);
        news_progressbar = (ProgressBar) findViewById(R.id.pb_news);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initData();
    }

    private void initData() {
        news_WebView.getSettings().setJavaScriptEnabled(true);
        news_WebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                news_progressbar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        news_WebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (isLoading){
                    news_progressbar.incrementProgressBy(newProgress - news_progressbar.getProgress());
                 if (newProgress > 45){
                     isLoading = false;
                     news_progressbar.setVisibility(View.GONE);
                 }
                }
            }
        });
        news_WebView.loadUrl(url);
    }
}
