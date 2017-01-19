package com.surpassli.www.myapp.ui.More.About;

import android.app.Activity;
import android.os.Bundle;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/1/19.
 */
public class About_us_activity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        getFragmentManager().beginTransaction().replace(R.id.fl_about_framelayout,new AboutFragment()).commit();
    }
}
