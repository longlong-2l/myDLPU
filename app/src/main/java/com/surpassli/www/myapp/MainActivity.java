package com.surpassli.www.myapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.support.adapter.Fragment.FragmentAdapter;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.EducationFragment;
import com.surpassli.www.myapp.ui.LifeFragment;
import com.surpassli.www.myapp.ui.LoginActivity;
import com.surpassli.www.myapp.ui.MoreFragment;
import com.surpassli.www.myapp.ui.MyFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

public class MainActivity extends FragmentActivity implements View.OnClickListener,MyFragment.sendData {
    private static final String TAG = "MainActivity";
    private List<Fragment> fragmentList;
    private ActivityMainBinding binding;
    private Intent intent;
    private TextView tv_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean isLogin = prefs.getBoolean("isLogin",false);
        int userId = prefs.getInt("userId",0);
        String username = prefs.getString("username",null);
        String token = prefs.getString("token",null);
        if (isLogin && !TextUtils.isEmpty(username) && !TextUtils.isEmpty(token) && userId!=0){
            AppVariables.isLogin = true;
            AppVariables.token = token;
            AppVariables.userId = userId;
            AppVariables.username = username;
        }
        initView();
        getData();
    }

    private void getData() {
        HttpUtil.sendGetOkhttp(AppApi.TIME, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: " + "获取系统授时失败：" + e.getMessage().toString());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"网络出现问题，请检查网络设置~~",Toast.LENGTH_SHORT).show();
                    }
                });
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
                        Log.i(TAG, "onResponse: " + "系统授时失败");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initView() {
        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
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
        binding.setOnetab(getString(R.string.life));
        binding.setTwotab(getString(R.string.education));
        binding.setThreetab(getString(R.string.more));
        binding.setFourtab(getString(R.string.my));
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
                    tv_toolbar.setText(getString(R.string.life));
                    break;
                case 1:
                    binding.tvTwotab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    tv_toolbar.setText(getString(R.string.education));
                    break;
                case 2:
                    binding.tvThreetab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                    tv_toolbar.setText(getString(R.string.more));
                    break;
                case 3:
                    if(!AppVariables.isLogin){
                        intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(intent,1);
                    }else {
                        binding.indexViewpager.setCurrentItem(3);
                        binding.tvFourtab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
                        tv_toolbar.setText(getString(R.string.my));
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
                tv_toolbar.setText(getString(R.string.life));
                binding.indexViewpager.setCurrentItem(0);
                binding.tvOnetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_twotab:
                tv_toolbar.setText(getString(R.string.education));
                binding.indexViewpager.setCurrentItem(1);
                binding.tvTwotab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_threetab:
                tv_toolbar.setText(getString(R.string.more));
                binding.indexViewpager.setCurrentItem(2);
                binding.tvThreetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
            case R.id.tv_fourtab:
                    if (!AppVariables.isLogin) {
                        intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivityForResult(intent, 1);
                    } else {
                        tv_toolbar.setText(getString(R.string.my));
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

    @Override
    public void sendData(String go) {
        if("onetab".equals(go)){
            binding.indexViewpager.setCurrentItem(0);
            binding.tvOnetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
        }
    }

    /**
     * 广播接收器
     */
//    public class MyBroadcastReceiver extends BroadcastReceiver{
//
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            String tab = intent.getStringExtra("tab");
//            if("onetab".equals(tab)){
//                binding.indexViewpager.setCurrentItem(0);
//                binding.tvOnetab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
//            }
//        }
//    }
}
