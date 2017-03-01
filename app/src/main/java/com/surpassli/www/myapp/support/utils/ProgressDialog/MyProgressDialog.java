package com.surpassli.www.myapp.support.utils.ProgressDialog;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by SurpassLi on 2017/2/2.
 */
public class MyProgressDialog {
    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context) {
        if (progressDialog == null || !progressDialog.isShowing()) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("正在用生命加载，请稍后...");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public static void closeDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
