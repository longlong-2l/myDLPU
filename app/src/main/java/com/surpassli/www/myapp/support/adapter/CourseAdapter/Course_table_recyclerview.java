package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Level_Grade.Course_Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/1/22.
 */
public class Course_table_recyclerview extends RecyclerView.Adapter<Course_table_recyclerview.ViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<Course_Table> mlist;

    public Course_table_recyclerview(ArrayList<Course_Table> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    //用于创建控件
    @Override
    public Course_table_recyclerview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_course_table, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(Course_table_recyclerview.ViewHolder holder, int position) {
        holder.tv_course_name.setText(mlist.get(position).getCourse_name());
        holder.tv_course_time.setText(mlist.get(position).getCourse_time());
        holder.tv_course_address.setText(mlist.get(position).getCourse_address());
        holder.tv_course_teacher.setText(mlist.get(position).getCourse_teacher());
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected final TextView tv_course_name;
        protected final TextView tv_course_time;
        protected final TextView tv_course_address;
        protected final TextView tv_course_teacher;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_course_name);
            tv_course_time = (TextView) itemView.findViewById(R.id.tv_course_time);
            tv_course_address = (TextView) itemView.findViewById(R.id.tv_course_address);
            tv_course_teacher = (TextView) itemView.findViewById(R.id.tv_course_teacher);
        }
    }
}
