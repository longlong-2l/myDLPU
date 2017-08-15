package com.surpassli.www.myapp.support.utils.html;

import com.surpassli.www.myapp.support.utils.common.TextUtil;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SurpassLi on 2016/2/3.
 * HtmlUtil
 */
public class HtmlUtil {
    private Document doc;

    /**
     * 构造方法
     * 以字符串为参数解析静态html页面
     * 字符编码为后编，可选。
     */
    public HtmlUtil(String htmlString, String... charsetName) throws UnsupportedEncodingException {
        if (!TextUtil.isNull(htmlString)) {
            if (charsetName.length == 1) {
                String e_htmlString = URLEncoder.encode(htmlString, charsetName[0]);
                this.doc = Jsoup.parse(e_htmlString);
            } else {
                    this.doc = Jsoup.parse(htmlString);
            }
        } else {
            this.doc = null;
        }
    }

    /**
     * 返回解析后的字符串
     * 参数（css选择样式）
     */
    public List<String> parseString(String cssQuery) {
        List<String> resultList = new ArrayList<>();
        Elements myElement = doc.select(cssQuery);
        for (int i = 0; i < myElement.size() ;i++) {
                resultList.add(myElement.get(i).text());
        }
        return resultList;
    }

    /**
     * 返回解析后的原生html
     * 参数（css选择样式）
     */
    public List<String> parseRawString(String cssQuery) {
        List<String> resultList = new ArrayList<>();
        Elements myElement = doc.select(cssQuery);
        for (int i = 0; i < myElement.size() ;i++) {
            resultList.add(myElement.get(i).toString());
        }
        return resultList;
    }

    /**
     *  指定属性查找
     * @param cssQuery cssQuery
     * @param cssAttr  cssAttr
     */
    public List<String> parseStringByAttr(String cssQuery, String cssAttr) {
        List<String> resultList = new ArrayList<>();
        Elements elements = doc.select(cssQuery);
        if (elements == null) {
            return resultList;
        }
        for (int i = 0 ; i < elements.size() ; i++) {
            resultList.add(elements.get(i).attr(cssAttr));
        }
        return resultList;
    }
}