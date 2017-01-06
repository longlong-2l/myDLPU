package com.surpassli.www.myapp;

import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.surpassli.www.myapp.databinding.ActivityMainBinding;
import com.surpassli.www.myapp.ui.EducationFragment;
import com.surpassli.www.myapp.ui.LifeFragment;
import com.surpassli.www.myapp.ui.MyFragment;
import com.surpassli.www.myapp.ui.NoNameFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager index_ViewPager;
    private List<Fragment> fragmentList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        initView();
    }

    private void initView() {

        fragmentList = new ArrayList<Fragment>();
        LifeFragment lifeFragment = new LifeFragment();
        MyFragment myFragment = new MyFragment();
        EducationFragment educationFragment = new EducationFragment();
        NoNameFragment noNameFragment = new NoNameFragment();

        fragmentList.add(lifeFragment);
        fragmentList.add(myFragment);
        fragmentList.add(educationFragment);
        fragmentList.add(noNameFragment);

    }
}
