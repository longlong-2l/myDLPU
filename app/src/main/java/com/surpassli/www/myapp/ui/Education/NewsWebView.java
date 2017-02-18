package com.surpassli.www.myapp.ui.Education;

import android.content.Intent;

import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/2/8.
 */
public class NewsWebView extends BaseWebViewActivity{
    private String url;

    @Override
    protected String getLink() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        url = intent.getStringExtra("url");
        return url;
    }
}
