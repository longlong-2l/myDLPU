package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.gson.Course_Result_bean;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by dell on 2017/1/17.
 */
public class Course_Result_Adapter extends RecyclerView.Adapter<Course_Result_Adapter.MyViewHolder> {

    private Context context;
    private List<Course_Result_bean> Datas;
    private LayoutInflater mInflater;

    public Course_Result_Adapter(Context context, List<Course_Result_bean> Datas) {
        this.context = context;
        this.Datas = Datas;
        mInflater = LayoutInflater.from(context);
    }

    //用于创建控件
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_course_result,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        //设置要显示的数据
        holder.tv_num.setText((CharSequence) Datas.get(position));
        holder.tv_No1_date.setText((CharSequence) Datas.get(position));
        holder.tv_course_num.setText((CharSequence) Datas.get(position));
        holder.tv_course_name.setText((CharSequence) Datas.get(position));
        holder.tv_course_score.setText((CharSequence) Datas.get(position));
        holder.tv_score_flag.setText((CharSequence) Datas.get(position));
        holder.tv_course_credit.setText((CharSequence) Datas.get(position));
        holder.tv_course_period.setText((CharSequence) Datas.get(position));
        holder.tv_exam_type.setText((CharSequence) Datas.get(position));
        holder.tv_course_property.setText((CharSequence) Datas.get(position));
        holder.tv_course_nature.setText((CharSequence) Datas.get(position));
        holder.tv_exam_nature.setText((CharSequence) Datas.get(position));
        holder.tv_again_term.setText((CharSequence) Datas.get(position));

    }

    @Override
    public int getItemCount() {
        return Datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        protected final TextView tv_num;
        protected final TextView tv_No1_date;
        protected final TextView tv_course_num;
        protected final TextView tv_course_name;
        protected final TextView tv_course_score;
        protected final TextView tv_score_flag;
        protected final TextView tv_course_credit;
        protected final TextView tv_course_period;
        protected final TextView tv_exam_type;
        protected final TextView tv_course_property;
        protected final TextView tv_course_nature;
        protected final TextView tv_exam_nature;
        protected final TextView tv_again_term;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_num = (TextView) itemView.findViewById(R.id.tv_num);
            tv_No1_date = (TextView) itemView.findViewById(R.id.tv_No1_date);
            tv_course_num = (TextView) itemView.findViewById(R.id.tv_course_num);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_course_name);
            tv_course_score = (TextView) itemView.findViewById(R.id.tv_course_score);
            tv_course_credit = (TextView) itemView.findViewById(R.id.tv_course_credit);
            tv_course_period = (TextView) itemView.findViewById(R.id.tv_course_period);
            tv_exam_type = (TextView) itemView.findViewById(R.id.tv_exam_type);
            tv_course_property = (TextView) itemView.findViewById(R.id.tv_course_property);
            tv_course_nature = (TextView) itemView.findViewById(R.id.tv_course_nature);
            tv_exam_nature = (TextView) itemView.findViewById(R.id.tv_exam_nature);
            tv_again_term = (TextView) itemView.findViewById(R.id.tv_again_term);
            tv_score_flag = (TextView) itemView.findViewById(R.id.tv_score_flag);
        }
    }
}
