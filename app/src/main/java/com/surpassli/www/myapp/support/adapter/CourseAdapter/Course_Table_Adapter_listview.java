package com.surpassli.www.myapp.support.adapter.CourseAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.model.Course_Table.Level_Course_Bean;

import java.util.List;

/**
 * Created by SurpassLi on 2017/1/21.
 */
public class Course_Table_Adapter_listview extends BaseAdapter {

    private List<Level_Course_Bean> mCourse_table_been_list;
    private LayoutInflater mInflater;

    public Course_Table_Adapter_listview(Context context, List<Level_Course_Bean> mCourse_table_been) {
        this.mCourse_table_been_list = mCourse_table_been;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mCourse_table_been_list.size();
    }

    @Override
    public Object getItem(int position) {
        return mCourse_table_been_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_level_exam, null);
            viewholder = new ViewHolder();
            viewholder.tv_order_number = (TextView) convertView.findViewById(R.id.tv_order_number);
            viewholder.tv_exam_name = (TextView) convertView.findViewById(R.id.tv_exam_name);
            viewholder.tv_grade_pen = (TextView) convertView.findViewById(R.id.tv_grade_pen);
            viewholder.tv_grade_computer = (TextView) convertView.findViewById(R.id.tv_grade_computer);
            viewholder.tv_grade_all = (TextView) convertView.findViewById(R.id.tv_grade_all);
            viewholder.tv_level_grade_pen = (TextView) convertView.findViewById(R.id.tv_level_grade_pen);
            viewholder.tv_level_grade_computer = (TextView) convertView.findViewById(R.id.tv_level_grade_computer);
            viewholder.tv_level_grade_all = (TextView) convertView.findViewById(R.id.tv_level_grade_all);
            viewholder.tv_exam_date = (TextView) convertView.findViewById(R.id.tv_exam_date);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }

        Level_Course_Bean myListBean = mCourse_table_been_list.get(position);
        viewholder.tv_order_number.setText(myListBean.getOrder_number());
        viewholder.tv_exam_name.setText(myListBean.getExam_name());
        viewholder.tv_grade_pen.setText(myListBean.getGrade_pen());
        viewholder.tv_grade_computer.setText(myListBean.getGrade_computer());
        viewholder.tv_grade_all.setText(myListBean.getGrade_all());
        viewholder.tv_level_grade_pen.setText(myListBean.getLevel_grade_pen());
        viewholder.tv_level_grade_computer.setText(myListBean.getLevel_grade_computer());
        viewholder.tv_level_grade_all.setText(myListBean.getLevel_grade_all());
        viewholder.tv_exam_date.setText(myListBean.getExam_date());
        return convertView;
    }

    public class ViewHolder {
        private TextView tv_order_number;
        private TextView tv_exam_name;
        private TextView tv_grade_pen;
        private TextView tv_grade_computer;
        private TextView tv_grade_all;
        private TextView tv_level_grade_pen;
        private TextView tv_level_grade_computer;
        private TextView tv_level_grade_all;
        private TextView tv_exam_date;
    }
}
