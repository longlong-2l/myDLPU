package com.surpassli.www.myapp.support.adapter.leisure;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.leisure.ScienceModel;
import com.surpassli.www.myapp.support.adapter.BaseListAdapter;

/**
 * Created by SurpassLi on 2017/8/9.
 * ScienceAdapter
 */

public class ScienceAdapter extends BaseListAdapter<ScienceModel,ScienceAdapter.VH> {

    public ScienceAdapter(Context mContext, ScienceModel model) {
        super(mContext, model);
    }

    @Override
    protected void updateView() {
        notifyDataSetChanged();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View ItemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_science, parent, false);
        return new VH(ItemView);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ScienceModel model = getItem(position);
        holder.scienceTitle.setText(model.getTitle());
        holder.comment.setText(String.valueOf(model.getReplies_count()));
        Glide.with(mContext).load(model.getImage_info().getUrl()).into(holder.scienceImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ScienceDetailsActivity.class);
//                EventBus.getDefault().postSticky(new EventModel<ScienceModel>(EVENT.SEND_MODEL_DETAIL,getItem(position)));
//                mContext.startActivity(intent);
            }
        });
    }

    class VH extends RecyclerView.ViewHolder {

        View itemView;
        TextView scienceTitle;
        TextView comment;
        ImageView scienceImage;

        public VH(View itemView) {
            super(itemView);
            this.itemView = itemView;
            scienceTitle = (TextView) itemView.findViewById(R.id.science_title);
            comment = (TextView) itemView.findViewById(R.id.tv_content);
            scienceImage = (ImageView) itemView.findViewById(R.id.iv_science);
        }
    }
}
