package com.surpassli.www.myapp.ui.More.ExerciseYard;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

/**
 * Created by SurpassLi on 2017/2/18.
 * ExerciseActivity
 */

public class ExerciseActivity extends BaseToolBarActivity {

    private int position;
    private String exercise_yard_name;
    private String content;
    private int[] exercise_yard_images = {R.drawable.exercise_yard00, R.drawable.exercise_yard01, R.drawable.exercise_yard02,
            R.drawable.exercise_yard03, R.drawable.exercise_yard04, R.drawable.exercise_yard05,
            R.drawable.exercise_yard06, R.drawable.exercise_yard07, R.drawable.exercise_yard08,
            R.drawable.exercise_yard09, R.drawable.exercise_yard10, R.drawable.exercise_yard11,
            R.drawable.exercise_yard12, R.drawable.exercise_yard13, R.drawable.exercise_yard14,
            R.drawable.exercise_yard16, R.drawable.exercise_yard16, R.drawable.exercise_yard17,
            R.drawable.exercise_yard18, R.drawable.exercise_yard19, R.drawable.exercise_yard20,
            R.drawable.exercise_yard21, R.drawable.exercise_yard22, R.drawable.exercise_yard23,
            R.drawable.exercise_yard24, R.drawable.exercise_yard25, R.drawable.exercise_yard25,};
    private TextView mContent;
    private TextView mEmpty;
    private ImageView mImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        initToolBar();
        Intent intent = getIntent();
        exercise_yard_name = intent.getStringExtra("exercise_yard");
        position = intent.getIntExtra("position", 0);
        content = intent.getStringExtra("content");
        setToolbarTitle(exercise_yard_name);
        initView();
        getData();
    }

    private void initView() {
        mImage = (ImageView) findViewById(R.id.iv_exercise_yard);
        mContent = (TextView) findViewById(R.id.tv_exercise_context);
        mEmpty = (TextView) findViewById(R.id.tv_exercise_empty);
    }

    private void getData() {
        if (exercise_yard_name != null && !exercise_yard_name.isEmpty()) {
            mEmpty.setVisibility(View.GONE);
            Glide.with(ExerciseActivity.this).load(exercise_yard_images[position]).crossFade(1500).into(mImage);
            if (content.length() < 3) {
                mContent.setGravity(Gravity.CENTER_HORIZONTAL);
                mContent.setText(exercise_yard_name);
            }else {
                mContent.setText(content);
            }
        } else {
            mImage.setVisibility(View.GONE);
            mContent.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        }
    }
}
