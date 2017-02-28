package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.gson.Course_Result_bean;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/18.
 */
public class Course_Adapter extends BaseAdapter {

    private Context context;
    private List<Course_Result_bean> Datas;
    private LayoutInflater mInflater;

    public Course_Adapter(Context context, List<Course_Result_bean> Datas) {
        this.context = context;
        this.Datas = Datas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return Datas.size();
    }

    @Override
    public Object getItem(int position) {
        return Datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_course_result, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            viewHolder.tv_No1_date = (TextView) convertView.findViewById(R.id.tv_No1_date);
            viewHolder.tv_course_num = (TextView) convertView.findViewById(R.id.tv_course_num);
            viewHolder.tv_course_name = (TextView) convertView.findViewById(R.id.tv_course_name);
            viewHolder.tv_course_score = (TextView) convertView.findViewById(R.id.tv_course_score);
            viewHolder.tv_score_flag = (TextView) convertView.findViewById(R.id.tv_score_flag);
            viewHolder.tv_course_credit = (TextView) convertView.findViewById(R.id.tv_course_credit);
            viewHolder.tv_course_period = (TextView) convertView.findViewById(R.id.tv_course_period);
            viewHolder.tv_exam_type = (TextView) convertView.findViewById(R.id.tv_exam_type);
            viewHolder.tv_course_property = (TextView) convertView.findViewById(R.id.tv_course_property);
            viewHolder.tv_course_nature = (TextView) convertView.findViewById(R.id.tv_course_nature);
            viewHolder.tv_exam_nature = (TextView) convertView.findViewById(R.id.tv_exam_nature);
            viewHolder.tv_again_term = (TextView) convertView.findViewById(R.id.tv_again_term);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Course_Result_bean course_result_bean = Datas.get(position);
        viewHolder.tv_num.setText( "序号：" + course_result_bean.getNum());
        viewHolder.tv_No1_date.setText( "首修学期：" + course_result_bean.getNo1_date());
        viewHolder.tv_course_num.setText( "课程编号：" + course_result_bean.getCourse());
        viewHolder.tv_course_name.setText( course_result_bean.getCourse_name());
        viewHolder.tv_course_score.setText( "成绩：" + course_result_bean.getCourse_score());
        viewHolder.tv_score_flag.setText( "成绩标识：" + course_result_bean.getScore_flag());
        viewHolder.tv_course_credit.setText( "学分：" + course_result_bean.getCourse_credit());
        viewHolder.tv_course_period.setText( "总学时：" + course_result_bean.getCourse_period());
        viewHolder.tv_exam_type.setText( "考核方式：" + course_result_bean.getExam_type());
        viewHolder.tv_course_property.setText( "课程属性：" + course_result_bean.getCourse_property());
        viewHolder.tv_course_nature.setText( "课程性质：" + course_result_bean.getCourse_nature());
        viewHolder.tv_exam_nature.setText( "考试性质：" + course_result_bean.getExam_nature());
        viewHolder.tv_again_term.setText( "补充学期：" + course_result_bean.getAgain_term());
        return convertView;
    }

    public class ViewHolder {
        protected TextView tv_num;
        protected TextView tv_No1_date;
        protected TextView tv_course_num;
        protected TextView tv_course_name;
        protected TextView tv_course_score;
        protected TextView tv_score_flag;
        protected TextView tv_course_credit;
        protected TextView tv_course_period;
        protected TextView tv_exam_type;
        protected TextView tv_course_property;
        protected TextView tv_course_nature;
        protected TextView tv_exam_nature;
        protected TextView tv_again_term;

    }
}
