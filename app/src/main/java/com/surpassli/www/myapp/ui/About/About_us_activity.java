package com.surpassli.www.myapp.ui.About;

import android.os.Bundle;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/1/19.
 *
 */
public class About_us_activity extends BaseToolBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        initToolBar();
        setToolbarTitle(getApplicationContext().getString(R.string.about));
        getFragmentManager().beginTransaction().replace(R.id.fl_about_framelayout,new AboutFragment()).commit();
    }
}
