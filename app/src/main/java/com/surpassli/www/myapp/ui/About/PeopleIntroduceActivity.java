package com.surpassli.www.myapp.ui.About;

import android.content.Intent;

import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by SurpassLi on 2017/8/31.
 * PeopleIntroduceActivity
 */

public class PeopleIntroduceActivity extends BaseWebViewActivity {

    @Override
    public void initView() {
        super.initView();
        Intent intent = getIntent();
        wv_base.loadUrl(intent.getStringExtra("url"));
    }

    @Override
    @Subscribe
    public void onEventComing(EventModel eventModel) {

    }
}
