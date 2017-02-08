package com.surpassli.www.myapp.support.adapter.NewsAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.News.Notice_Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/2/8.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;

    private List<Notice_Model> mNoticeModel;

    private LayoutInflater mInflater;

    public interface OnItemClickListener {
        void onItemClick(View view ,int position,String url);

        void onItemLongClick(View view ,int position,String url);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }

    public NewsAdapter(Context mContext, ArrayList<Notice_Model> mNoticeModel) {
        this.mContext = mContext;
        this.mNoticeModel = mNoticeModel;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_news, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(NewsAdapter.ViewHolder holder, int position) {
        holder.tv_title.setText(mNoticeModel.get(position).getTitle());
        holder.tv_content_url.setText(mNoticeModel.get(position).getUrl());
        setUpItemEvent(holder);
    }

    @Override
    public int getItemCount() {
        return mNoticeModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView tv_title;
        protected final TextView tv_content_url;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_content_url = (TextView) itemView.findViewById(R.id.tv_content_url);
        }
    }

    protected  void setUpItemEvent(final ViewHolder viewHolder){
        if (mOnItemClickListener!=null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = viewHolder.tv_content_url.getText().toString();
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView,layoutPosition,url);
                }
            });
            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    String url = viewHolder.tv_content_url.getText().toString();
                    int layoutPosition = viewHolder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(viewHolder.itemView,layoutPosition,url);
                    return false;
                }
            });
        }
    }
}
