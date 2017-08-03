package com.surpassli.www.myapp.ui.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.surpassli.www.myapp.support.utils.ActivityManager;

/**
 * Created by SurpassLi on 2017/8/3.
 * Description: BaseActivity
 */

public class BaseActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.getInstance().finishOneActivity(this);
    }
}
