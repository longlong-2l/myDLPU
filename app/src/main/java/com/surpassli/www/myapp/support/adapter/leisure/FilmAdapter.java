package com.surpassli.www.myapp.support.adapter.leisure;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.event.EVENT;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.model.leisure.FilmModel;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;
import com.surpassli.www.myapp.ui.leisure.FilmDetailActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by SurpassLi on 2017/8/14.
 * FilmAdapter
 */

public class FilmAdapter extends BaseListAdapter<FilmModel, FilmAdapter.VH> {

    public FilmAdapter(Context mContext, FilmModel model) {
        super(mContext, model);
    }

    @Override
    protected void updateView() {
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.card_film, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        FilmModel model = getItem(position);
        holder.fileTitle.setText(model.getTitle());
        holder.readingCount.setText(model.getReadingCount());
        Glide.with(mContext).load(model.getTopPic()).into(holder.fileImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, FilmDetailActivity.class);
                EventBus.getDefault().postSticky(new EventModel<FilmModel>(EVENT.SEND_MODEL_DETAIL,getItem(position)));
                mContext.startActivity(intent);
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {
        View itemView;
        TextView fileTitle;
        TextView readingCount;
        ImageView fileImage;

        public VH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            fileTitle = (TextView) itemView.findViewById(R.id.film_title);
            readingCount = (TextView) itemView.findViewById(R.id.tv_film_num);
            fileImage = (ImageView) itemView.findViewById(R.id.iv_film);
        }
    }
}
