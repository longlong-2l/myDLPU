package com.surpassli.www.myapp.support.utils.common;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.surpassli.www.myapp.InitApp;
import com.surpassli.www.myapp.R;

public class TextUtil {
    public static boolean isNull(String str){
        if(str == null || str.replaceAll("\\s*", "").equals("")){
            return true;
        }
        return false;
    }
    public static void copyToClipboard(View view, String info) {
        ClipboardManager cm = (ClipboardManager) InitApp.AppContext.getSystemService(InitApp.AppContext.CLIPBOARD_SERVICE);
        ClipData cd = ClipData.newPlainText("msg", info);
        cm.setPrimaryClip(cd);
        Snackbar.make(view, R.string.notify_info_copied, Snackbar.LENGTH_SHORT).show();
    }


}
