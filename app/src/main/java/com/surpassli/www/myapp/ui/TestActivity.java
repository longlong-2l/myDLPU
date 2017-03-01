package com.surpassli.www.myapp.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.News.Notice_Model;
import com.surpassli.www.myapp.support.adapter.NewsAdapter.NewsAdapter;
import com.surpassli.www.myapp.support.adapter.NewsAdapter.NewsAdapterListView;
import com.surpassli.www.myapp.support.utils.EducationUtils;
import com.surpassli.www.myapp.support.utils.HttpUtil;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/2/8.
 * 这是一个测试用的Activity，没有在程序中有任何体现。
 */
public class TestActivity extends Activity{
    private NewsAdapterListView mNewsAdapterListView;
    private ListView lv_news;
    private RecyclerView news_RecycleView;
    private NewsAdapter mNewAdapter;
    private ArrayList<Notice_Model> mNotice_ModelsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        lv_news = (ListView) findViewById(R.id.lv_news);
        news_RecycleView = (RecyclerView) findViewById(R.id.rv_news);
        getData();
    }

    private void getData() {
        mNotice_ModelsList = new ArrayList<Notice_Model>();
        HttpUtil.sendGetOkhttp(AppApi.NOTICE, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(TestActivity.this,"网络出现问题...",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "onResponse: " + "通知公告数据获取成功");
                String data = response.body().string();
                mNotice_ModelsList = EducationUtils.handNew_Notice(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNewsAdapterListView =  new NewsAdapterListView(TestActivity.this,mNotice_ModelsList);
                        lv_news.setAdapter(mNewsAdapterListView);

                        mNewAdapter = new NewsAdapter(TestActivity.this,mNotice_ModelsList);
                        news_RecycleView.setAdapter(mNewAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(TestActivity.this,LinearLayoutManager.VERTICAL,false);
                        news_RecycleView.setLayoutManager(layoutManager);
                    }
                });
            }
        });
    }
}
