package com.surpassli.www.myapp.ui.Home;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseActivity;

/**
 * Created by SurpassLi on 2017/8/28.
 * NoticesActivity
 */

public class NoticesDetailActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_detail);
    }
}
