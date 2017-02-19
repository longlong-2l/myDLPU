package com.surpassli.www.myapp.ui.More.ExerciseYard;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.support.adapter.TagCloudViewAdapter;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.HttpsUtils;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

public class ExerciseYardActivity extends BaseToolBarActivity {
    private TagCloudView mTagCloudView;
    private ProgressBar mProgreBar;
    private TagCloudViewAdapter mCloudAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_yard);
        initToolBar();
        setToolbarTitle(getString(R.string.exercise_yard));
        initView();
        String[] qw = {getString(R.string.comprehensive_building_A), getString(R.string.comprehensive_building_B),
                getString(R.string.comprehensive_building_C), getString(R.string.school_clothing),
                getString(R.string.school_art_layout), getString(R.string.school_mechanical_engineering),
                getString(R.string.school_food), getString(R.string.school_chemical_engineering),
                getString(R.string.school_manage), getString(R.string.school_graduate_student),
                getString(R.string.school_textiles_and_materials), getString(R.string.school_club),
                getString(R.string.school_gymnasium), getString(R.string.school_continuing_learning),
                getString(R.string.school_library), getString(R.string.school_paper_building), getString(R.string.original_office_building),
                getString(R.string.test_base), getString(R.string.school_food_technology_research_center), getString(R.string.school_international_education),
                getString(R.string.school_biotechnology),getString(R.string.food_building)};
        mCloudAdapter = new TagCloudViewAdapter(ExerciseYardActivity.this,qw);
        mTagCloudView.setAdapter(mCloudAdapter);
        HttpUtil.sendGetOkhttp("http://1.linjie.applinzi.com/dlpu/index.php", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "onFailure: ====="+"网络问题");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.i("onResponse", "onResponse:====== "+ res);
            }
        });
    }

    private void initView() {
        mTagCloudView = (TagCloudView) findViewById(R.id.tagView);
        mProgreBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
