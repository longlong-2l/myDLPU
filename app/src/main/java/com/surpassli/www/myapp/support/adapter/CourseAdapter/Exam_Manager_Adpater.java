package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Account.Exam_Manager;
import com.surpassli.www.myapp.model.Course_Table.Level_Grade;

import java.util.List;

/**
 * Created by SurpassLi on 2017/2/13.
 */
public class Exam_Manager_Adpater extends RecyclerView.Adapter<Exam_Manager_Adpater.ViewHolder>{

    private List<Exam_Manager> list;
    private LayoutInflater mInflater;

    public Exam_Manager_Adpater(Context context, List<Exam_Manager> list) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Exam_Manager_Adpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_exam_manager,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Exam_Manager_Adpater.ViewHolder holder, int position) {
        holder.tv_order_number.setText("序号：" + list.get(position).getOrder_number());
        holder.tv_exam_number.setText("考试场次：" + list.get(position).getExam_number());
        holder.tv_course_number.setText("课程编号：" + list.get(position).getCourse_number());
        holder.tv_course_name.setText("课程名称：" + list.get(position).getCourse_name());
        holder.tv_exam_time.setText("考试时间：" + list.get(position).getExam_time());
        holder.tv_exam_address.setText("考场：：" + list.get(position).getExam_address());
        holder.tv_exam_card_number.setText("准卡证号：" + list.get(position).getExam_card_number());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_order_number;
        protected TextView tv_exam_number;
        protected TextView tv_course_number;
        protected TextView tv_course_name;
        protected TextView tv_exam_time;
        protected TextView tv_exam_address;
        protected TextView tv_exam_card_number;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_number = (TextView) itemView.findViewById(R.id.tv_order_number);
            tv_exam_number = (TextView) itemView.findViewById(R.id.tv_exam_number);
            tv_course_number = (TextView) itemView.findViewById(R.id.tv_course_number);
            tv_course_name = (TextView) itemView.findViewById(R.id.tv_course_name);
            tv_exam_time = (TextView) itemView.findViewById(R.id.tv_exam_time);
            tv_exam_address = (TextView) itemView.findViewById(R.id.tv_exam_address);
            tv_exam_card_number = (TextView) itemView.findViewById(R.id.tv_exam_card_number);
        }
    }
}
