package com.surpassli.www.myapp.ui.ExerciseYard;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.moxun.tagcloudlib.view.TagCloudView;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.database.dao.ExerciseDAO;
import com.surpassli.www.myapp.model.ExerciseYard;
import com.surpassli.www.myapp.support.adapter.TagCloudViewAdapter;
import com.surpassli.www.myapp.support.utils.AccountUtility;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.Base.BaseToolBarActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

public class ExerciseYardActivity extends BaseToolBarActivity {
    private TagCloudView mTagCloudView;
    private ProgressBar mProgreBar;
    private TagCloudViewAdapter mCloudAdapter;
    private ArrayList<String> mExerciseYard_String;
    private ArrayList<ExerciseYard> mExerciseYard;
    private ExerciseDAO exerciseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_yard);
        initToolBar();
        setToolbarTitle(getString(R.string.exercise_yard));
        initView();
        getExerciseYard();
    }

    /**
     * 获取活动场地的数据
     */
    public void getExerciseYard() {
        exerciseDAO = new ExerciseDAO(ExerciseYardActivity.this);
        mExerciseYard_String = new ArrayList<String>();
        mExerciseYard = new ArrayList<ExerciseYard>();
//        mExerciseYard_String = exerciseDAO.selectName();
        mExerciseYard = exerciseDAO.select();
        if (mExerciseYard!=null && mExerciseYard.size()>0){
//            mCloudAdapter = new TagCloudViewAdapter(ExerciseYardActivity.this, mExerciseYard_String,"String");
            mCloudAdapter = new TagCloudViewAdapter(ExerciseYardActivity.this, mExerciseYard);
            mTagCloudView.setAdapter(mCloudAdapter);
        }else{
            getExerciseYardFromSever();
        }
    }

    /**
     * 从服务器获取活动场地的数据
     */
    private void getExerciseYardFromSever() {
        exerciseDAO = new ExerciseDAO(ExerciseYardActivity.this);
        HttpUtil.sendGetOkhttp("http://1.linjie.applinzi.com/dlpu/index.php", new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("onFailure", "onFailure: ====="+"网络问题");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(ExerciseYardActivity.this,"网络出现问题，请检查网络设置...",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String res = response.body().string();
                Log.i("onResponse", "onResponse: ====="+"网络无问题");
                boolean result = AccountUtility.handExerciseYard(res,ExerciseYardActivity.this);
                if(result) {
//                    mExerciseYard_String = exerciseDAO.selectName();
                    mExerciseYard = exerciseDAO.select();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            mCloudAdapter = new TagCloudViewAdapter(ExerciseYardActivity.this, mExerciseYard_String,"String");
                            mCloudAdapter = new TagCloudViewAdapter(ExerciseYardActivity.this, mExerciseYard);
                            mTagCloudView.setAdapter(mCloudAdapter);
                        }
                    });
                }
                    else
                    Log.i("onResponse", "onResponse: "+"数据库读取失败");
            }
        });
    }

    private void initView() {
        mTagCloudView = (TagCloudView) findViewById(R.id.tagView);
        mProgreBar = (ProgressBar) findViewById(R.id.progressBar);
    }
}
