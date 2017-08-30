package com.surpassli.www.myapp.support.htmlparse.home;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Created by SurpassLi on 2017/8/29.
 * TrendsDetailParse
 */

public class TrendsDetailParse {
    private String parseStr;

    public TrendsDetailParse(String string) {
        super();
        this.parseStr = string;
        this.parseStr=string;
        parseData();
    }

    public String parseData(){
        Document doc = Jsoup.parse(parseStr);
        return doc.select("div[id=detail]").toString();
    }
}
