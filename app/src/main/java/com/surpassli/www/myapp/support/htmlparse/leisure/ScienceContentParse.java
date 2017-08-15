package com.surpassli.www.myapp.support.htmlparse.leisure;

import android.util.Log;

/**
 * Created by SurpassLi on 2017/8/14.
 * ScienceContentParse
 */

public class ScienceContentParse {
    private static String STR_CUT_LEFT = "<div class=\"container article-page\">";//分割左边界
    private static String STR_CUT_RIGHT = "<div class=\"recommend-articles\">";//分割右边界
    private String parseStr = null;//待解析字符串
    private String endStr = null;//已解析字符串

    public ScienceContentParse(String parseStr) {
        this.parseStr = parseStr;
        parseData();
    }

    public String getParseStr() {
        return parseStr;
    }

    public String getEndStr() {
        return endStr;
    }

    /*
     * 解析数据，并把解析得到的最终的字符串放入endStr中
     */
    private void parseData() {
        String first_cut[] = parseStr.split(STR_CUT_LEFT);
        if (first_cut.length > 1) {
            String second_cut[] = first_cut[1].split(STR_CUT_RIGHT);
            if (second_cut.length > 1) {
                endStr = STR_CUT_LEFT + second_cut[0] + "</div>";
            } else {
                Log.e("ERROR", "第二次解析出错，未找到有效分割元素");
            }
        } else {
            Log.e("ERROR", "第一次解析出错，未找到有效分割元素");
        }
    }
}
