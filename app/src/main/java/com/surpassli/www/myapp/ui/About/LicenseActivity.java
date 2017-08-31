package com.surpassli.www.myapp.ui.About;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.BaseWebViewActivity;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by SurpassLi on 2017/2/6.
 * 开源协议
 */
public class LicenseActivity extends BaseWebViewActivity{

    @Override
    public void initView() {
        super.initView();
        super.title = getString(R.string.license);
        wv_base.loadUrl("file:///android_asset/licenses.html");
    }

    @Override
    @Subscribe
    public void onEventComing(EventModel eventModel) {

    }
}
