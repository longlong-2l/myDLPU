package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Course_Table.Level_Grade;

import java.util.List;

/**
 * Created by SurpassLi on 2017/2/13.
 */
public class Level_Grade_Adpater extends RecyclerView.Adapter<Level_Grade_Adpater.ViewHolder>{

    private List<Level_Grade> list;
    private LayoutInflater mInflater;

    public Level_Grade_Adpater(Context context, List<Level_Grade> list) {
        this.list = list;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public Level_Grade_Adpater.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_level_exam,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(Level_Grade_Adpater.ViewHolder holder, int position) {
        holder.tv_order_number.setText("序号：" + list.get(position).getOrder_number());
        holder.tv_exam_name.setText("考试课程等级：" + list.get(position).getExam_name());
        holder.tv_grade_pen.setText("分数类成绩笔试：" + list.get(position).getGrade_pen());
        holder.tv_grade_computer.setText("分数类成绩机试：" + list.get(position).getGrade_computer());
        holder.tv_grade_all.setText("分数类成绩总成绩：" + list.get(position).getGrade_all());
        holder.tv_level_grade_pen.setText("等级类成绩笔试：：" + list.get(position).getLevel_grade_pen());
        holder.tv_level_grade_computer.setText("等级类成绩机试：" + list.get(position).getLevel_grade_computer());
        holder.tv_level_grade_all.setText("等级类成绩总成绩：" + list.get(position).getLevel_grade_all());
        holder.tv_exam_date.setText("考试时间：" + list.get(position).getExam_date());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_order_number;
        protected TextView tv_exam_name;
        protected TextView tv_grade_pen;
        protected TextView tv_grade_computer;
        protected TextView tv_grade_all;
        protected TextView tv_level_grade_pen;
        protected TextView tv_level_grade_computer;
        protected TextView tv_level_grade_all;
        protected TextView tv_exam_date;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_order_number = (TextView) itemView.findViewById(R.id.tv_order_number);
            tv_exam_name = (TextView) itemView.findViewById(R.id.tv_exam_name);
            tv_grade_pen = (TextView) itemView.findViewById(R.id.tv_grade_pen);
            tv_grade_computer = (TextView) itemView.findViewById(R.id.tv_grade_computer);
            tv_grade_all = (TextView) itemView.findViewById(R.id.tv_grade_all);
            tv_level_grade_pen = (TextView) itemView.findViewById(R.id.tv_level_grade_pen);
            tv_level_grade_computer = (TextView) itemView.findViewById(R.id.tv_level_grade_computer);
            tv_level_grade_all = (TextView) itemView.findViewById(R.id.tv_level_grade_all);
            tv_exam_date = (TextView) itemView.findViewById(R.id.tv_exam_date);
        }
    }
}
