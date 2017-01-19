package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.ui.Account.Course_Result_Activity;
import com.surpassli.www.myapp.ui.Account.School_Roll_Activity;

/**
 * Created by SurpassLi on 2017/1/6.
 */
public class MyFragment extends Fragment implements View.OnClickListener{

//    private final static String TAG = "MyFragment";
//    private FragmentMyBinding myBinding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        return view;
    }

    private void initView() {
        view.findViewById(R.id.tv_xuejikapian).setOnClickListener(this);
        view.findViewById(R.id.tv_course_grade).setOnClickListener(this);
        view.findViewById(R.id.tv_level_grade).setOnClickListener(this);
        view.findViewById(R.id.tv_course_table).setOnClickListener(this);
        view.findViewById(R.id.tv_current_week).setOnClickListener(this);
        view.findViewById(R.id.tv_exam_plan).setOnClickListener(this);
        view.findViewById(R.id.tv_password_reset).setOnClickListener(this);
        view.findViewById(R.id.tv_login_out).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_xuejikapian:
                startActivity(new Intent(getActivity(), School_Roll_Activity.class));
                break;
            case R.id.tv_course_grade:
                startActivity( new Intent(getActivity(), Course_Result_Activity.class));
                break;
        }
    }
}
