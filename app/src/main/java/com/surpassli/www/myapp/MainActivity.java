package com.surpassli.www.myapp;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.surpassli.www.myapp.adapter.FragmentAdapter;
import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.ui.EducationFragment;
import com.surpassli.www.myapp.ui.LifeFragment;
import com.surpassli.www.myapp.ui.MyFragment;
import com.surpassli.www.myapp.ui.NoNameFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private List<Fragment> fragmentList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        initView();
    }

    private void initView() {

        fragmentList = new ArrayList<Fragment>();
        LifeFragment lifeFragment = new LifeFragment();
        EducationFragment educationFragment = new EducationFragment();
        NoNameFragment noNameFragment = new NoNameFragment();
        MyFragment myFragment = new MyFragment();

        fragmentList.add(lifeFragment);
        fragmentList.add(educationFragment);
        fragmentList.add(noNameFragment);
        fragmentList.add(myFragment);

        FragmentAdapter fragmentadapter = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
        binding.indexViewpager.setAdapter(fragmentadapter);
        binding.indexViewpager.addOnPageChangeListener(pageChangeListener);
        binding.setOnetab("生活");
        binding.setTwotab("教育");
        binding.setThreetab("NoName");
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
                    binding.tvFourtab.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.app_blue));
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
                binding.indexViewpager.setCurrentItem(3);
                binding.tvFourtab.setTextColor(ContextCompat.getColor(MainActivity.this,R.color.app_blue));
                break;
        }
    }

    private void resetTextView(){
        binding.tvOnetab.setTextColor(Color.GRAY);
        binding.tvTwotab.setTextColor(Color.GRAY);
        binding.tvThreetab.setTextColor(Color.GRAY);
        binding.tvFourtab.setTextColor(Color.GRAY);
    }
}
