package com.surpassli.www.myapp.ui.More.ExerciseYard;

import android.content.Intent;
import android.os.Bundle;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/18.
 */

public class ExerciseActivity extends BaseToolBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Intent intent = getIntent();
        String exercise_yard = intent.getStringExtra("exercise_yard");
        initToolBar();
        setToolbarTitle(exercise_yard);
    }
}
