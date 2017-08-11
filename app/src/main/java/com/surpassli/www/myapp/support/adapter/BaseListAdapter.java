package com.surpassli.www.myapp.support.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.surpassli.www.myapp.model.IModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/8/9.
 * BaseListAdapter
 */

public abstract class BaseListAdapter<M extends IModel, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private static final String TAG = "BaseListAdapter";
    protected List<M> mItems;
    protected Context mContext;

    protected abstract void updateView();

    public BaseListAdapter(Context mContext, M model) {
        Log.d(TAG, "BaseListAdapter: ");
        this.mContext = mContext;
    }

    public BaseListAdapter(Context mContext, List<M> modelList) {
        this.mContext = mContext;
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return mItems == null ? 0 : mItems.size();
    }

    protected M getItem(int position){
        Log.d(TAG, "getItem: ");
        return mItems.get(position);
    }

    public void newList(List<M> list){
        if(list == null){
            return;
        }
        if(mItems == null){
            mItems = new ArrayList<>();
        }else {
            mItems.clear();
        }
        mItems.addAll(list);
        updateView();
    }
}
