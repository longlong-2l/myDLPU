package com.surpassli.www.myapp.support.htmlparse.Education;

import com.surpassli.www.myapp.api.AppApi;
import com.surpassli.www.myapp.model.Education.EducationTrendsModel;
import com.surpassli.www.myapp.support.utils.html.HtmlUtil;

import org.jsoup.Jsoup;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2017/8/31.
 * TrendsParse
 */

public class EducationTrendsParse {
    private String parseStr;
    private static final String ALL_ITEM_CSS = "ul[class=more_list]";//所有列表选择css
    private static final String ITEM_CSS = "li";
    private List<EducationTrendsModel> endList;

    public EducationTrendsParse(String string) {
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
            String all = htmlUtil.parseToString(ALL_ITEM_CSS);
            htmlUtil = new HtmlUtil(all);
            List<String> titleString = htmlUtil.parseRawString(ITEM_CSS);
            for (String resultString : titleString) {
                //解析内容Url
                contentUrl.add(Jsoup.parse(resultString).select("a").attr("href"));
                String content = Jsoup.parse(resultString).body().text();
                String contentCut[] = content.split(" ");
                //解析标题
                title.add(contentCut[0]);
                //解析时间
                time.add(contentCut[1]);
            }
            for (int i = 0; i < titleString.size(); i++) {
                endList.add(new EducationTrendsModel(AppApi.SCHOOL+ contentUrl.get(i),title.get(i),time.get(i)));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public List<EducationTrendsModel> getEndList() {
        return endList;
    }
}
