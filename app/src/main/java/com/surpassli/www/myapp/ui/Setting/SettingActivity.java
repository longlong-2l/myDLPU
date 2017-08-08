package com.surpassli.www.myapp.ui.Setting;

import android.os.Bundle;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/8/7.
 * SettingActivity
 */

public class SettingActivity extends BaseToolBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initToolBar();
        setToolbarTitle(getApplicationContext().getString(R.string.settings));
        getFragmentManager().beginTransaction().replace(R.id.setting_fl,new SettingFragment()).commit();
    }
}
