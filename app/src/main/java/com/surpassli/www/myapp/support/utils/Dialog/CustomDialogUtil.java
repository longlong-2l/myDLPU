package com.surpassli.www.myapp.support.utils.Dialog;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.surpassli.www.myapp.R;

/**
 * Created by SurpassLi on 2017/1/20.
 */
public class CustomDialogUtil {
    private AlertDialog alertDialog;
    private TextView tv_title;
    private TextView tv_message;
    private TextView tv_cancel;
    private TextView tv_positive;

    public CustomDialogUtil(Context context) {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.show();

        Window window = alertDialog.getWindow();
        window.setContentView(R.layout.dialog_normal_util);

        tv_title = (TextView) window.findViewById(R.id.tv_title);
        tv_message = (TextView) window.findViewById(R.id.tv_message);
        tv_cancel = (TextView) window.findViewById(R.id.tv_cancel);
        tv_positive = (TextView) window.findViewById(R.id.tv_positive);
    }

    public void PositiveClickListener(final View.OnClickListener listener){
        tv_positive.setOnClickListener(listener);
    }

    public void CancelClickListener(final View.OnClickListener listener){
        tv_cancel.setOnClickListener(listener);
        tv_cancel.setVisibility(View.VISIBLE);
    }

    public TextView getTv_title() {
        return tv_title;
    }

    public void setTv_title(String tv_title) {
        this.tv_title.setText(tv_title);
    }

    public TextView getTv_message() {
        return tv_message;
    }

    public void setTv_message(String tv_message) {
        this.tv_message.setText(tv_message);
    }

    public TextView getTv_positive() {
        return tv_positive;
    }

    public void setTv_positive(String tv_positive) {
        this.tv_positive.setText(tv_positive);
    }

    public void dismiss(){
        alertDialog.dismiss();
    }
}
