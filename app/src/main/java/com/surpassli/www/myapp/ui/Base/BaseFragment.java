package com.surpassli.www.myapp.ui.Base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.event.EventModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by SurpassLi on 2017/8/7.
 * BaseFragment
 */

public abstract class BaseFragment extends Fragment {
    public Activity activity;
    protected View parentView = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    public Activity getMyActivity() {
        return activity;
    }

    private void loadConfig(){
    }

    protected abstract int getLayoutID(); //界面加载
    protected  abstract void init();      //数据绑定
    public abstract String getTitle();    //获取标题
    public abstract void onEventComing(EventModel eventModel);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        parentView = View.inflate(getContext(),getLayoutID(),null);
        init();
        loadConfig();
        return parentView;
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onEventMainThread(EventModel eventModel){
        if(eventModel != null){
            onEventComing(eventModel);
        }
    }
}
