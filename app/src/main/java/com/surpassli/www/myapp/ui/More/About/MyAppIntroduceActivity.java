package com.surpassli.www.myapp.ui.More.About;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/1/19.
 * MyAppIntroduceActivity
 */
public class MyAppIntroduceActivity extends BaseWebViewActivity {

    protected String getLink() {
        super.title=getApplicationContext().getString(R.string.app_introduce);
        return "file:///android_asset/MyAppIntroduction.html";
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
