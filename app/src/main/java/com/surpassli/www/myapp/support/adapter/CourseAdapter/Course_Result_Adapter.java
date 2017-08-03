package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Account.Course_Result_bean;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/17.
 * Test
 */
public class Course_Result_Adapter extends RecyclerView.Adapter<Course_Result_Adapter.MyViewHolder> {

    private static final String TAG = "Course_Result_Adapter";
    private List<Course_Result_bean> Datas;
    private LayoutInflater mInflater;

    public Course_Result_Adapter(Context context, List<Course_Result_bean> Datas) {
        this.Datas = Datas;
        Log.i(TAG, "Course_Result_Adapter:list数据源 "+Datas.toString());
        mInflater = LayoutInflater.from(context);
    }

    //用于创建控件
    @Override
    public Course_Result_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_course_result,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    //为控件设置数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        //设置要显示的数据
        holder.tv_num.setText("序号：" + Datas.get(position).getNum());
        holder.tv_No1_date.setText( "首修学期：" + Datas.get(position).getNo1_date());
        holder.tv_course_num.setText("课程编号：" + Datas.get(position).getCourse());
        holder.tv_course_name.setText( "课程名称：" + Datas.get(position).getCourse_name());
        holder.tv_course_score.setText("成绩：" + Datas.get(position).getCourse_score());
        holder.tv_score_flag.setText( "成绩标识：" + Datas.get(position).getScore_flag());
        holder.tv_course_credit.setText( "学分：" + Datas.get(position).getCourse_credit());
        holder.tv_course_period.setText( "总学时：" + Datas.get(position).getCourse_period());
        holder.tv_exam_type.setText("考核方式：" + Datas.get(position).getExam_type());
        holder.tv_course_property.setText("课程属性：" + Datas.get(position).getCourse_property());
        holder.tv_course_nature.setText( "课程性质：" + Datas.get(position).getCourse_nature());
        holder.tv_exam_nature.setText( "考试性质：" + Datas.get(position).getExam_nature());
        holder.tv_again_term.setText( "补充学期：" + Datas.get(position).getAgain_term());
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
            tv_score_flag = (TextView) itemView.findViewById(R.id.tv_score_flag);
            tv_course_credit = (TextView) itemView.findViewById(R.id.tv_course_credit);
            tv_course_period = (TextView) itemView.findViewById(R.id.tv_course_period);
            tv_exam_type = (TextView) itemView.findViewById(R.id.tv_exam_type);
            tv_course_property = (TextView) itemView.findViewById(R.id.tv_course_property);
            tv_course_nature = (TextView) itemView.findViewById(R.id.tv_course_nature);
            tv_exam_nature = (TextView) itemView.findViewById(R.id.tv_exam_nature);
            tv_again_term = (TextView) itemView.findViewById(R.id.tv_again_term);
        }
    }
}
