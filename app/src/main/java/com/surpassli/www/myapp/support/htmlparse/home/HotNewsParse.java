package com.surpassli.www.myapp.support.htmlparse.home;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Home.Hot_News_Model;
import com.surpassli.www.myapp.support.utils.html.HtmlUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/8/15.
 * NoticeParse
 */

public class HotNewsParse {


    private String parseStr;
    private static final String ALL_ITEM_CSS = "ul[class=more_list]";//所有列表选择css
    private static final String ITEM_CSS = "li";
    private List<Hot_News_Model> endList;

    public HotNewsParse(String string) {
        super();
        this.parseStr = string;
        endList = new ArrayList();
        parseData();
    }

    private void parseData() {
        try {
            List<String> title = new ArrayList<>();
            List<String> time = new ArrayList<>();
            List<String> contentUrl = new ArrayList<>();

            HtmlUtil htmlUtil = new HtmlUtil(parseStr);
            List all_list = htmlUtil.parseRawString(ALL_ITEM_CSS);
            if (all_list == null || all_list.isEmpty()) {
                return;
            }
            htmlUtil = new HtmlUtil(all_list.get(0).toString());
            List<String> titleString = htmlUtil.parseRawString(ITEM_CSS);
            for (String resultString : titleString) {
                //解析内容Url
                String urlFirstCut[] = resultString.split("<li><a href=\"");
                if (urlFirstCut.length > 1) {
                    String urlSecondCut[] = urlFirstCut[1].split("\" target=\"_blank\"");
                    if (urlSecondCut.length > 1) {
                        contentUrl.add(urlSecondCut[0]);
                    }
                }
                //解析时间
                String timeFirstCut[] = resultString.split("more_news_time\">");
                if (timeFirstCut.length > 1) {
                    String timeSecondCut[] = timeFirstCut[1].split("</span>");
                    if (timeSecondCut.length > 1) {
                        time.add(timeSecondCut[0]);
                    }
                }
                //解析标题
                String titleFirstCut[] = resultString.split("target=\"_blank\">");
                if (titleFirstCut.length > 1) {
                    String titleSecondCut[] = titleFirstCut[1].split("<span class=\"more_news_time\">");
                    if (titleSecondCut.length > 1) {
                        title.add(titleSecondCut[0]);
                    }
                }
            }
            for (int i = 0; i < titleString.size(); i++) {
                endList.add(new Hot_News_Model(AppApi.SCHOOL+ contentUrl.get(i),title.get(i),time.get(i)));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public List<Hot_News_Model> getEndList() {
        return endList;
    }
}
