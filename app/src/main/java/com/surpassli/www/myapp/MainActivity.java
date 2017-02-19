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
import android.widget.ImageView;
import android.widget.TabWidget;
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
    private ImageView iv_one_tab;
    private ImageView iv_two_tab;
    private ImageView iv_three_tab;
    private ImageView iv_four_tab;

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
//        iv_one_tab = (ImageView) findViewById(R.id.iv_one_tab);
//        iv_two_tab = (ImageView) findViewById(R.id.iv_two_tab);
//        iv_three_tab = (ImageView) findViewById(R.id.iv_three_tab);
//        iv_four_tab = (ImageView) findViewById(R.id.iv_four_tab);
//        tv_toolbar = (TextView) findViewById(R.id.tv_toolbar);
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
        binding.ivOneTab.setOnClickListener(this);
        binding.ivTwoTab.setOnClickListener(this);
        binding.ivThreeTab.setOnClickListener(this);
        binding.ivFourTab.setOnClickListener(this);
//        iv_one_tab.setOnClickListener(this);
//        iv_two_tab.setOnClickListener(this);
//        iv_three_tab.setOnClickListener(this);
//        iv_four_tab.setOnClickListener(this);
    }

    public ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            resetTextView();
            switch (position) {
                case 0:
                    binding.ivOneTab.setImageResource(R.drawable.indexed);
//                    iv_one_tab.setImageResource(R.drawable.indexed);
                    binding.tvToolbar.setText(getString(R.string.life));
//                    tv_toolbar.setText(getString(R.string.life));
                    break;
                case 1:
                    binding.ivTwoTab.setImageResource(R.drawable.educationed);
//                    iv_two_tab.setImageResource(R.drawable.educationed);
                    binding.tvToolbar.setText(getString(R.string.education));
//                    tv_toolbar.setText(getString(R.string.education));
                    break;
                case 2:
                    binding.ivThreeTab.setImageResource(R.drawable.mored);
//                    iv_three_tab.setImageResource(R.drawable.mored);
//                    tv_toolbar.setText(getString(R.string.more));
                    binding.tvToolbar.setText(getString(R.string.more));
                    break;
                case 3:
                    if(!AppVariables.isLogin){
                        intent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivityForResult(intent,1);
                    }else {
                        binding.indexViewpager.setCurrentItem(3);
                        binding.ivFourTab.setImageResource(R.drawable.myed);
//                        iv_four_tab.setImageResource(R.drawable.myed);
//                        tv_toolbar.setText(getString(R.string.my));
                        binding.tvToolbar.setText(getString(R.string.my));
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
        if(v==binding.ivOneTab){
//            tv_toolbar.setText(getString(R.string.life));
            binding.tvToolbar.setText(getString(R.string.life));
            binding.indexViewpager.setCurrentItem(0);
        }else if(v==binding.ivTwoTab){
//            tv_toolbar.setText(getString(R.string.education));
            binding.tvToolbar.setText(getString(R.string.education));
            binding.indexViewpager.setCurrentItem(1);
        }else if(v==binding.ivThreeTab){
//            tv_toolbar.setText(getString(R.string.more));
            binding.tvToolbar.setText(getString(R.string.more));
            binding.indexViewpager.setCurrentItem(2);
        }else if(v==binding.ivFourTab) {
            if (!AppVariables.isLogin) {
                intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, 1);
            } else {
//                tv_toolbar.setText(getString(R.string.my));
                binding.tvToolbar.setText(getString(R.string.my));
                binding.indexViewpager.setCurrentItem(3);
            }
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
        }else if(requestCode == 1 && resultCode == 2){
            String status = data.getStringExtra("status");
            if(status.equals("Success")){
                binding.indexViewpager.setCurrentItem(0);
            }
        }
    }

    private void resetTextView(){
        binding.ivOneTab.setImageResource(R.drawable.index);
        binding.ivTwoTab.setImageResource(R.drawable.education);
        binding.ivThreeTab.setImageResource(R.drawable.more);
        binding.ivFourTab.setImageResource(R.drawable.my);
//        iv_one_tab.setImageResource(R.drawable.index);
//        iv_two_tab.setImageResource(R.drawable.education);
//        iv_three_tab.setImageResource(R.drawable.more);
//        iv_four_tab.setImageResource(R.drawable.my);
    }

    @Override
    public void sendData(String go) {
        if("onetab".equals(go)){
            binding.indexViewpager.setCurrentItem(0);
            binding.ivOneTab.setImageResource(R.drawable.indexed);
//           iv_one_tab.setImageResource(R.drawable.indexed);
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
