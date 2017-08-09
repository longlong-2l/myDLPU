package com.surpassli.www.myapp.ui.leisure;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.News.Notice_Model;
import com.surpassli.www.myapp.model.leisure.ScienceModel;
import com.surpassli.www.myapp.support.adapter.leisure.ScienceAdapter;
import com.surpassli.www.myapp.support.utils.HttpUtil;
import com.surpassli.www.myapp.ui.Base.BaseListFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceFragment
 */

public class ScienceFragment extends BaseListFragment {

    private ScienceModel model;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_noname, container, false);
        HttpUtil.sendGetOkhttp(AppApi.SCIENCE_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final ArrayList<ScienceModel> arrayList = new ArrayList<>();
                try {
                    JSONObject jsonObject = new JSONObject(response.body().string());
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        String jsonObject1 = jsonArray.getJSONObject(i).toString();
                        ScienceModel entity = new Gson().fromJson(jsonObject1,ScienceModel.class);
                        arrayList.add(entity);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.newList(arrayList);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    @Override
    public String getTitle() {
        return InitApp.AppContext.getString(R.string.science);
    }

    @Override
    public void initView() {
        model.loadFromNet();
    }

    @Override
    public void bindAdapter() {
        model = new ScienceModel();
        adapter = new ScienceAdapter(getActivity(),model);
        recyclerView.setAdapter(adapter);
        displayLoading();
    }

    @Override
    public void addHeader() {

    }
}
