package com.surpassli.www.myapp.ui.About;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by SurpassLi on 2017/1/19.
 * MyAppIntroduceActivity
 */
public class MyAppIntroduceActivity extends BaseWebViewActivity {

    @Override
    public void initView() {
        super.initView();
        super.title = getString(R.string.app_introduce);
        wv_base.loadUrl("file:///android_asset/MyAppIntroduction.html");
    }

    @Override
    @Subscribe
    public void onEventComing(EventModel eventModel) {

    }
}
