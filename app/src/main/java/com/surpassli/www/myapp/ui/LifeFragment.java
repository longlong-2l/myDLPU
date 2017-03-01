package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.News.Notice_Model;
import com.surpassli.www.myapp.support.adapter.NewsAdapter.NewsAdapter;
import com.surpassli.www.myapp.support.utils.EducationUtils;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.support.utils.ProgressDialog.MyProgressDialog;
import com.surpassli.www.myapp.ui.Education.NewsWebView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/1/6.
 */
public class LifeFragment extends Fragment {
    private View view;
    private ArrayList<Notice_Model> mNotice_ModelsList2;
    private NewsAdapter mNewAdapter;
    private SwipeRefreshLayout news_swiSwipeRefreshLayout;
    private RecyclerView rv_school_news;
    private RollPagerView mRollPagerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_life, container, false);
        rv_school_news = (RecyclerView) view.findViewById(R.id.rv_school_news);
        mRollPagerView = (RollPagerView) view.findViewById(R.id.roll_view_pager);

        mRollPagerView.setPlayDelay(3000);
        mRollPagerView.setAnimationDurtion(500);
        mRollPagerView.setAdapter(new rollViewPagerAdapter());
        mRollPagerView.setHintView(new ColorPointHintView(getActivity(),Color.BLUE, Color.WHITE));

        mNotice_ModelsList2 = new ArrayList<Notice_Model>();
        SharedPreferences pre = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String school_news = pre.getString("school_news",null);
        if(school_news!=null) {
            mNotice_ModelsList2 = EducationUtils.handNew_Notice(school_news);
            setView();
        } else {
            MyProgressDialog.showProgressDialog(getActivity());
            getData();
        }
        news_swiSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_Refresh);
        news_swiSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        news_swiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mRollPagerView.setAdapter(new rollViewPagerAdapter());
    }

    private void getData() {
        HttpUtil.sendGetOkhttp(AppApi.NEWS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("LifeFragment", "onFailure: "+"新闻动态数据获取出问题了");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络出现问题...", Toast.LENGTH_SHORT).show();
                        news_swiSwipeRefreshLayout.setRefreshing(false);
                        MyProgressDialog.closeDialog();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String res =  response.body().string();
                mNotice_ModelsList2 = EducationUtils.handNew_Notice(res);
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                editor.putString("school_news",res);
                editor.apply();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        news_swiSwipeRefreshLayout.setRefreshing(false);
                        MyProgressDialog.closeDialog();
                        setView();
                    }
                });
            }
        });
    }

    private void setView(){
        mNewAdapter = new NewsAdapter(getActivity(), mNotice_ModelsList2);
        rv_school_news.setAdapter(mNewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_school_news.setItemAnimator(new DefaultItemAnimator());
        rv_school_news.setLayoutManager(layoutManager);
        mNewAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String url) {
                TextView title = (TextView) view.findViewById(R.id.tv_title);
                String text = title.getText().toString();
                Intent intent = new Intent(getActivity(), NewsWebView.class);
                intent.putExtra("title",text);
                intent.putExtra("url", url);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, String url) {
            }
        });
    }

    private class rollViewPagerAdapter extends StaticPagerAdapter{
        private int[] images = {R.mipmap.school1,R.mipmap.school2,R.mipmap.school3,R.mipmap.school4};

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView imageview = new ImageView(container.getContext());
            imageview.setImageResource(images[position]);
            imageview.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageview.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            return imageview;
        }
        @Override
        public int getCount() {
            return images.length;
        }
    }
}
