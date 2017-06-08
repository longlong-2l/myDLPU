package com.surpassli.www.myapp.ui.More.Scenery;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;
import com.surpassli.www.myapp.ui.More.ExerciseYard.ExerciseActivity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SurpassLi on 2017/2/19.
 */

public class SceneryContentActivity extends BaseToolBarActivity{

    private ImageView iv_scerency;
    private TextView mEmpty;
    private String scenery_name;
    private int position;
    private Map<String,Object> scenery_images2 = new HashMap<String,Object>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery_content);
        initToolBar();
        setToolbarTitle("校园景色");
        if(scenery_images2 == null ||scenery_images2.isEmpty()) {
            scenery_images2.put("水电管理部", R.drawable.scenery01);
            scenery_images2.put("食品院后草坪", R.drawable.scenery02);
            scenery_images2.put("造纸楼旁雕塑", R.drawable.scenery03);
            scenery_images2.put("东山小红房", R.drawable.scenery04);
            scenery_images2.put("工大苗圃", R.drawable.scenery06);
            scenery_images2.put("二食堂林荫道", R.drawable.scenery07);
            scenery_images2.put("服院旁花坛", R.drawable.scenery08);
            scenery_images2.put("门诊部小道", R.drawable.scenery09);
            scenery_images2.put("雕塑室前雕塑", R.drawable.scenery10);
            scenery_images2.put("服院前林荫道", R.drawable.scenery11);
            scenery_images2.put("艺院前花坛", R.drawable.scenery12);
            scenery_images2.put("体育场旁小道", R.drawable.scenery13);
            scenery_images2.put("艺院侧面雕塑", R.drawable.scenery14);
            scenery_images2.put("Life Spring", R.drawable.scenery15);
            scenery_images2.put("原球场旁林荫道", R.drawable.scenery17);
            scenery_images2.put("林荫走廊", R.drawable.scenery18);
            scenery_images2.put("体育场爬山虎", R.drawable.scenery19);
            scenery_images2.put("工大水池", R.drawable.scenery20);
        }
        Intent intent = getIntent();
        scenery_name = intent.getStringExtra("scenery_name");
//        position = intent.getIntExtra("position", 0);
//        setToolbarTitle(exercise_yard_name);
        initView();
        getData();
    }

    private void initView() {
        iv_scerency = (ImageView) findViewById(R.id.iv_scenery);
        mEmpty = (TextView) findViewById(R.id.tv_scenery_empty);
    }

    private void getData() {
            if (scenery_name != null && !scenery_name.isEmpty()) {
                mEmpty.setVisibility(View.GONE);
                Glide.with(SceneryContentActivity.this).load(scenery_images2.get(scenery_name)).crossFade(1000).into(iv_scerency);
            } else {
                iv_scerency.setVisibility(View.GONE);
                mEmpty.setVisibility(View.VISIBLE);
            }
    }
}
