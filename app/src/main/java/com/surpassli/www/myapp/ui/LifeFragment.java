package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
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
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.News.Notice_Model;
import com.surpassli.www.myapp.support.adapter.NewsAdapter.NewsAdapter;
import com.surpassli.www.myapp.support.utils.EducationUtils;
import com.surpassli.www.myapp.support.utils.HttpUtil;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_life, container, false);
        rv_school_news = (RecyclerView) view.findViewById(R.id.rv_school_news);
        news_swiSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_Refresh);
        news_swiSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        news_swiSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        getData();
        return view;
    }

    private void getData() {
        mNotice_ModelsList2 = new ArrayList<Notice_Model>();
        HttpUtil.sendGetOkhttp(AppApi.NEWS, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("LifeFragment", "onFailure: "+"新闻动态数据获取出问题了");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "网络出现问题...", Toast.LENGTH_SHORT).show();
                        news_swiSwipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               String res =  response.body().string();
                mNotice_ModelsList2 = EducationUtils.handNew_Notice(res);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        news_swiSwipeRefreshLayout.setRefreshing(false);
                        initView();
                    }
                });
            }
        });
    }

    private void initView(){
        mNewAdapter = new NewsAdapter(getActivity(), mNotice_ModelsList2);
        rv_school_news.setAdapter(mNewAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_school_news.setItemAnimator(new DefaultItemAnimator());
//      news_RecycleView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        rv_school_news.setLayoutManager(layoutManager);
        mNewAdapter.setOnItemClickListener(new NewsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String url) {
                Intent intent = new Intent(getActivity(), NewsWebView.class);
                intent.putExtra("url", url);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position, String url) {
//                Toast.makeText(getActivity(), position + "click:" + "url=" + url, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
