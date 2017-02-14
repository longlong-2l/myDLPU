package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.More.About.About_us_activity;
import com.surpassli.www.myapp.ui.More.BaiduMap.LocationActivity;
import com.surpassli.www.myapp.ui.More.Weather.WeatherActivity;

/**
 * Created by SurpassLi on 2017/1/6.
 */
public class MoreFragment extends Fragment implements View.OnClickListener{
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_noname,container,false);
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.tv_About_us).setOnClickListener(this);
        view.findViewById(R.id.tv_weatherSearch).setOnClickListener(this);
        view.findViewById(R.id.tv_school_location).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_About_us:
                startActivity(new Intent(getActivity(), About_us_activity.class));
                break;
            case R.id.tv_weatherSearch:
                startActivity(new Intent(getActivity(), WeatherActivity.class));
                break;
            case R.id.tv_school_location:
                startActivity(new Intent(getActivity(), LocationActivity.class));
                break;
        }
    }
}
