package com.surpassli.www.myapp.support.htmlparse.home;

import android.util.Log;

import com.surpassli.www.myapp.model.Home.Trends_Model;
import com.surpassli.www.myapp.support.utils.html.HtmlUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/8/29.
 * TrendsDetailParse
 */

public class TrendsDetailParse {
    private String parseStr;
    private static final String ALL_ITEM_CSS = "div[id=detail]";//所有列表选择css
    private static final String ITEM_CSS = "li";
    private List<Trends_Model> endList;

    public TrendsDetailParse(String string) {
        super();
        this.parseStr = string;
        endList = new ArrayList();
        this.parseStr=string;
        parseData();
    }

    public String parseData(){
        Document doc = Jsoup.parse(parseStr);
        String result=doc.select("div[id=detail]").toString();
        Log.e("jsoup","-----"+result);
        return result;
    }
}
