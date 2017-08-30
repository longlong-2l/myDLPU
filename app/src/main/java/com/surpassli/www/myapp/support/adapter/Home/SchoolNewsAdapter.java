package com.surpassli.www.myapp.support.adapter.Home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Home.School_News_Model;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;
import com.surpassli.www.myapp.ui.Home.SchoolDetailActivity;


/**
 * Created by SurpassLi on 2017/8/15.
 * SchoolNewsAdapter
 */

public class SchoolNewsAdapter extends BaseListAdapter<School_News_Model, SchoolNewsAdapter.VH> {

    public SchoolNewsAdapter(Context mContext, School_News_Model model) {
        super(mContext, model);
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
        final School_News_Model school_news_model = getItem(position);
        holder.title.setText(school_news_model.getSchool_news_title());
        holder.time.setText(school_news_model.getSchool_news_time());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SchoolDetailActivity.class);
                intent.putExtra("url", school_news_model.getSchool_news_url());
                intent.putExtra("title", mContext.getResources().getString(R.string.school_news));
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
