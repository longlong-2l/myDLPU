package com.surpassli.www.myapp.ui.More.Scenery;

import android.os.Bundle;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/19.
 */

public class SceneryContentActivity extends BaseToolBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery_content);
        initToolBar();
        setToolbarTitle("校园景色");
    }
}
