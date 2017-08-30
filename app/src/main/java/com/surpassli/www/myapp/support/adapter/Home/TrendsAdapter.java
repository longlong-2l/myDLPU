package com.surpassli.www.myapp.support.adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Home.Trends_Model;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;
import com.surpassli.www.myapp.ui.Home.SchoolDetailActivity;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeAdapter
 */

public class TrendsAdapter extends BaseListAdapter<Trends_Model, TrendsAdapter.VH> {

    public TrendsAdapter(Context mContext, Trends_Model model) {
        super(mContext, model);
    }

    public TrendsAdapter(Context mContext, List<Trends_Model> modelList) {
        super(mContext, modelList);
    }

    @Override
    protected void updateView() {
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final Trends_Model school_trends_model = getItem(position);
        holder.title.setText(school_trends_model.getTrends_title());
        holder.time.setText(school_trends_model.getTrends_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SchoolDetailActivity.class);
                intent.putExtra("url", school_trends_model.getTrends_url());
                intent.putExtra("title", mContext.getResources().getString(R.string.school_trends));
                mContext.startActivity(intent);
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {
        View itemView;
        TextView title;
        TextView time;

        public VH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            title = (TextView) itemView.findViewById(R.id.tv_news_title);
            time = (TextView) itemView.findViewById(R.id.tv_news_time);
        }
    }
}
