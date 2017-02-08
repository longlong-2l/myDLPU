package com.surpassli.www.myapp.ui.More.About;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.databinding.ActivityIntroduceWebviewBinding;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class MyAppIntroduceActivity extends Activity {

    private ActivityIntroduceWebviewBinding introducesBinding;
    private boolean isloading = true;
    private ProgressBar myProgressBar;
    private WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introducesBinding = DataBindingUtil.setContentView(this, R.layout.activity_introduce_webview);
        initdatas();
    }

    private void initdatas() {
        myProgressBar = introducesBinding.pbIntroduces;
        myWebView = introducesBinding.wvIntroduce;

        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                myProgressBar.setVisibility(View.GONE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        myWebView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if(isloading){
                    myProgressBar.incrementProgressBy(newProgress - myProgressBar.getProgress());
                    if(newProgress > 80){
                        isloading = false;
                        myProgressBar.setVisibility(View.GONE);
                    }
                }
            }
        });

        myWebView.loadUrl("file:///android_asset/MyAppIntroduction.html");
    }
}
