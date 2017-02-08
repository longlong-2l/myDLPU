package com.surpassli.www.myapp.support.adapter.NewsAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.News.Notice_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/2/8.
 */
public class NewsAdapterListView extends BaseAdapter {

    private List<Notice_Model> mNoticeModel;

    private LayoutInflater mInflater;;

    public NewsAdapterListView(Context context, ArrayList<Notice_Model> mNoticeModel) {
        this.mNoticeModel = mNoticeModel;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mNoticeModel.size();
    }

    @Override
    public Object getItem(int position) {
        return mNoticeModel.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_news, null);
            viewholder = new ViewHolder();
            viewholder.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
            viewholder.tv_url = (TextView) convertView.findViewById(R.id.tv_content_url);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Notice_Model mNoticeModelListBean = mNoticeModel.get(position);
        viewholder.tv_title.setText(mNoticeModelListBean.getTitle());
        viewholder.tv_url.setText(mNoticeModelListBean.getUrl());
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_title;
        private TextView tv_url;
    }
}
