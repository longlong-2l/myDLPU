package com.surpassli.www.myapp.ui.Home;

import android.content.Intent;

import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/8/28.
 * SchoolHotNewsActivity
 */

public class SchoolHotNewsDetailActivity extends BaseWebViewActivity {


    protected String getLink() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        return intent.getStringExtra("url");
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
