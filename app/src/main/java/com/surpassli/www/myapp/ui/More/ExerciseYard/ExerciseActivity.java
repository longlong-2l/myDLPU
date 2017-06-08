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
 */

public class ExerciseActivity extends BaseToolBarActivity {

    private int position;
    private String exercise_yard_name;
    private String content;
    private int[] exercise_yard_images = {R.mipmap.exercise_yard00, R.mipmap.exercise_yard01, R.mipmap.exercise_yard02,
            R.mipmap.exercise_yard03, R.mipmap.exercise_yard04, R.mipmap.exercise_yard05,
            R.mipmap.exercise_yard06, R.mipmap.exercise_yard07, R.mipmap.exercise_yard08,
            R.mipmap.exercise_yard09, R.mipmap.exercise_yard10, R.mipmap.exercise_yard11,
            R.mipmap.exercise_yard12, R.mipmap.exercise_yard13, R.mipmap.exercise_yard14,
            R.mipmap.exercise_yard16, R.mipmap.exercise_yard16, R.mipmap.exercise_yard17,
            R.mipmap.exercise_yard18, R.mipmap.exercise_yard19, R.mipmap.exercise_yard20,
            R.mipmap.exercise_yard21, R.mipmap.exercise_yard22, R.mipmap.exercise_yard23};
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
            Glide.with(ExerciseActivity.this).load(exercise_yard_images[position]).into(mImage);
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
