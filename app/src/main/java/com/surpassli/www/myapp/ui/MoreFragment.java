package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;
import com.surpassli.www.myapp.ui.About.About_us_activity;
import com.surpassli.www.myapp.ui.About.FeedBackActivity;
import com.surpassli.www.myapp.ui.ExerciseYard.ExerciseYardActivity;
import com.surpassli.www.myapp.ui.Scenery.SceneryActivity;
import com.surpassli.www.myapp.ui.life.WeatherActivity;

/**
 * Created by SurpassLi on 2017/1/6.
 * MoreFragment
 */
public class MoreFragment extends TopNavigationFragment implements View.OnClickListener{
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
        view.findViewById(R.id.tv_exercise_yard).setOnClickListener(this);
        view.findViewById(R.id.tv_scenery).setOnClickListener(this);
        view.findViewById(R.id.tv_feedback).setOnClickListener(this);
        view.findViewById(R.id.tv_waiting1).setOnClickListener(this);
        view.findViewById(R.id.tv_waiting2).setOnClickListener(this);
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
                break;
            case R.id.tv_exercise_yard:
                startActivity(new Intent(getActivity(), ExerciseYardActivity.class));
                break;
            case R.id.tv_scenery:
                startActivity(new Intent(getActivity(), SceneryActivity.class));
                break;
            case R.id.tv_feedback:
                startActivity(new Intent(getActivity(), FeedBackActivity.class));
                break;
            case R.id.tv_waiting1:
                Intent intent = new Intent(getContext(),TestActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_waiting2:
                Toast.makeText(getActivity(),"正在拼命开发中，敬请期待...",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public static MoreFragment getInstance(){
        return new MoreFragment();
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
