package com.surpassli.www.myapp;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.support.adapter.Fragment.FragmentAdapter;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.EducationFragment;
import com.surpassli.www.myapp.ui.LifeFragment;
import com.surpassli.www.myapp.ui.LoginActivity;
import com.surpassli.www.myapp.ui.MyFragment;
import com.surpassli.www.myapp.ui.MoreFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private List<Fragment> fragmentList;
    private ActivityMainBinding binding;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initView();
        getData();
    }

    private void getData() {
        HttpUtil.sendGetOkhttp(AppApi.TIME, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取系统授时失败：" + e.getMessage().toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i(TAG, "onResponse: " + "获取系统授时成功");
                String result = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    if("Success".equals(jsonObject.getString("message"))){
                        AppVariables.time = jsonObject.getInt("time");
                        AppVariables.time_cha = AppVariables.time - (System.currentTimeMillis() / 1000);
                    }else{
                        Log.i(TAG, "onResponse: " + "状态不对");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        fragmentList = new ArrayList<Fragment>();
        LifeFragment lifeFragment = new LifeFragment();
        EducationFragment educationFragment = new EducationFragment();
        MoreFragment moreFragment = new MoreFragment();
        MyFragment myFragment = new MyFragment();

        fragmentList.add(lifeFragment);
        fragmentList.add(educationFragment);
        fragmentList.add(moreFragment);
        fragmentList.add(myFragment);

        FragmentAdapter fragmentadapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        binding.indexViewpager.setAdapter(fragmentadapter);
        binding.indexViewpager.addOnPageChangeListener(pageChangeListener);
        binding.setOnetab("生活");
        binding.setTwotab("教育");
        binding.setThreetab("更多");
        binding.setFourtab("我的");
        binding.tvOnetab.setOnClickListener(this);
        binding.tvTwotab.setOnClickListener(this);
        binding.tvThreetab.setOnClickListener(this);
        binding.tvFourtab.setOnClickListener(this);
    }

    public ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            resetTextView();
            switch (position) {
                case 0:
                    binding.tvOnetab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    break;
                case 1:
                    binding.tvTwotab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    break;
                case 2:
                    binding.tvThreetab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    break;
                case 3:
                    if(!AppVariables.isLogin){
                        intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(intent,1);
                    }else {
                        binding.indexViewpager.setCurrentItem(3);
                        binding.tvFourtab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    }
                    break;
            }
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    public void onClick(View v) {
        resetTextView();
        switch (v.getId()) {
            case R.id.tv_onetab:
                binding.indexViewpager.setCurrentItem(0);
                binding.tvOnetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_twotab:
                binding.indexViewpager.setCurrentItem(1);
                binding.tvTwotab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_threetab:
                binding.indexViewpager.setCurrentItem(2);
                binding.tvThreetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_fourtab:
                if(!AppVariables.isLogin){
                    intent = new Intent(MainActivity.this,LoginActivity.class);
                    startActivityForResult(intent,1);
                }else {
                    binding.indexViewpager.setCurrentItem(3);
                    binding.tvFourtab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1){
            String status = data.getStringExtra("status");
            if(status.equals("Success")){
                binding.indexViewpager.setCurrentItem(0);
            }
        }
    }

    private void resetTextView(){
        binding.tvOnetab.setTextColor(Color.GRAY);
        binding.tvTwotab.setTextColor(Color.GRAY);
        binding.tvThreetab.setTextColor(Color.GRAY);
        binding.tvFourtab.setTextColor(Color.GRAY);
    }
}
