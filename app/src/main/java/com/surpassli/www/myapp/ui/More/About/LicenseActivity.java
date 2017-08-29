package com.surpassli.www.myapp.ui.More.About;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

/**
 * Created by SurpassLi on 2017/2/6.
 * 开源协议
 */
public class LicenseActivity extends BaseWebViewActivity{
    protected String getLink() {
        super.title = getString(R.string.license);
        return "file:///android_asset/licenses.html";
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
