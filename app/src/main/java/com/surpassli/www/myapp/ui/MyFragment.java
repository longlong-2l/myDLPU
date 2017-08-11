package com.surpassli.www.myapp.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.surpassli.www.myapp.AppVariables;
import com.surpassli.www.myapp.R;
import com.surpassli.www.myapp.database.dao.AccountDAO;
import com.surpassli.www.myapp.event.EventModel;
import com.surpassli.www.myapp.support.utils.ActivityCollector;
import com.surpassli.www.myapp.support.utils.Dialog.CustomDialogUtil;
import com.surpassli.www.myapp.ui.Account.ChangedPassWord_Activity;
import com.surpassli.www.myapp.ui.Account.Course_Result_Activity;
import com.surpassli.www.myapp.ui.Account.Course_Table_Activity;
import com.surpassli.www.myapp.ui.Account.CurrentWeekActivity;
import com.surpassli.www.myapp.ui.Account.ExamManagerActivity;
import com.surpassli.www.myapp.ui.Account.Level_Grade_Activity;
import com.surpassli.www.myapp.ui.Account.School_Roll_Activity;
import com.surpassli.www.myapp.ui.Base.TopNavigationFragment;

/**
 * Created by SurpassLi on 2017/1/6.
 *
 */
public class MyFragment extends TopNavigationFragment implements View.OnClickListener{
    private View view;
    private sendData sendedData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        initView();
        return view;
    }

    interface sendData{
        void sendData(String go);
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
            case R.id.tv_level_grade:
                startActivity(new Intent(getActivity(), Level_Grade_Activity.class));
                break;
            case R.id.tv_login_out:
                final CustomDialogUtil customDialogUtil = new CustomDialogUtil(getActivity());
                customDialogUtil.setTv_title("友情提示");
                customDialogUtil.setTv_message("确定退出登录吗？");
                customDialogUtil.PositiveClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AppVariables.clear();
//                        PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().clear().apply();
                        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getActivity()).edit();
                        editor.remove("isLogin");
                        editor.remove("userId");
                        editor.remove("username");
                        editor.remove("token");
                        editor.apply();
                        AccountDAO accountDAO = new AccountDAO(getContext());
                        accountDAO.delete_user(AppVariables.userId);
                        customDialogUtil.dismiss();
                        sendedData = (sendData) getActivity();
                        sendedData.sendData("onetab");
                        ActivityCollector.finishAll();
                    }
                });
                customDialogUtil.CancelClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        customDialogUtil.dismiss();
                    }
                });
                break;
            case R.id.tv_course_table:
                startActivity(new Intent(getActivity(), Course_Table_Activity.class));
                break;
            case R.id.tv_password_reset:
                startActivity(new Intent(getActivity(), ChangedPassWord_Activity.class));
                break;
            case R.id.tv_exam_plan:
                startActivity(new Intent(getActivity(), ExamManagerActivity.class));
                break;
            case R.id.tv_current_week:
                startActivity(new Intent(getActivity(), CurrentWeekActivity.class));
                break;
        }
    }

    public static MyFragment getInstance(){
        return new MyFragment();
    }

    @Override
    public void onEventComing(EventModel eventModel) {

    }
}
