package com.surpassli.www.myapp.support.adapter.Education;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Education.EducationNoticesModel;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;
import com.surpassli.www.myapp.ui.Home.SchoolDetailActivity;

import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * EducationNoticesAdapter
 */

public class EducationNoticesAdapter extends BaseListAdapter<EducationNoticesModel, EducationNoticesAdapter.VH> {
    public EducationNoticesAdapter(Context mContext, EducationNoticesModel model) {
        super(mContext, model);
    }

    public EducationNoticesAdapter(Context mContext, List<EducationNoticesModel> modelList) {
        super(mContext, modelList);
    }

    @Override
    protected void updateView() {
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false);
        return new EducationNoticesAdapter.VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final EducationNoticesModel education_notices_model = getItem(position);
        holder.title.setText(education_notices_model.getNotices_title());
        holder.time.setText(education_notices_model.getNotices_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SchoolDetailActivity.class);
                intent.putExtra("url", education_notices_model.getNotices_url());
                intent.putExtra("title", mContext.getResources().getString(R.string.education_notices));
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
