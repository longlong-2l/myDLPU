package com.surpassli.www.myapp.ui.Base;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.surpassli.www.myapp.R;

public class BaseToolBarActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tool_bar);
    }

    protected void initToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TypedArray array = getTheme().obtainStyledAttributes(new int[] {
                android.R.attr.colorPrimary,
        });
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
            toolbar.setBackgroundColor(array.getColor(0,0xFFFFFF));
        else
            toolbar.setBackgroundColor(Color.BLACK);
        array.recycle();
    }

    public void setToolbarTitle(String title){
          getSupportActionBar().setTitle(title);
    }
}
