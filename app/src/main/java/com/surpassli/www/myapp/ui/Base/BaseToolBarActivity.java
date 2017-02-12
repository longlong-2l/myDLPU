package com.surpassli.www.myapp.ui.Base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.surpassli.www.myapp.R;

public class BaseToolBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_tool_bar);
    }

    public void setToolbarTitle(String title){

    }
}
