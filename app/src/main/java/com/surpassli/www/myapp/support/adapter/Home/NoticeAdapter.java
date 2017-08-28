package com.surpassli.www.myapp.support.adapter.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Home.Notice_Model;
import com.surpassli.www.myapp.model.Home.School_News_Model;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeAdapter
 */

public class NoticeAdapter extends BaseListAdapter<Notice_Model, NoticeAdapter.VH> {

    public NoticeAdapter(Context mContext, Notice_Model model) {
        super(mContext, model);
    }

    public NoticeAdapter(Context mContext, List<Notice_Model> modelList) {
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
        Notice_Model school_notices_model = getItem(position);
        holder.title.setText(school_notices_model.getNotice_title());
        holder.time.setText(school_notices_model.getNotice_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
