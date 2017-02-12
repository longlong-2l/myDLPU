package com.surpassli.www.myapp.ui.More.About;

import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class MyAppIntroduceActivity extends BaseWebViewActivity {

    @Override
    protected String getLink() {
        return "file:///android_asset/MyAppIntroduction.html";
    }
}
