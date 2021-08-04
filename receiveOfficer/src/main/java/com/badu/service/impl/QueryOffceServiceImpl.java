package com.badu.service.impl;

import com.badu.service.IQueryOffceService;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guo qian
 */
@Service
public class QueryOffceServiceImpl implements IQueryOffceService {

    @Override
    public String queryOffcer() {
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        String url = "http://cx.ahzsks.cn/pugao/pglq2021_in-00b8bd3f-d5c8-4f90-b4e7-1b76cf147bbd.php";
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 6000000);
        getMethod.addRequestHeader("Content-Type", "application/json");
        try {
            httpClient.executeMethod(getMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            result = getMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document document = Jsoup.parse(result);
        Elements select = document.select("#yzm");
        String outerHtml = document.outerHtml();
        String s = select.toString();
        int index = outerHtml.indexOf(s);
        int length = s.length();
        String substring = outerHtml.substring(index + length, index + length + 4);
        String cookie = getMethod.getResponseHeader("Set-Cookie").getValue();
        String substring1 = cookie.substring(0, cookie.indexOf(" path") - 1);


        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        String url1 = "http://cx.ahzsks.cn/pugao/pglq2021_out-00b8bd3f-d5c8-4f90-b4e7-1b76cf147bbd.php";
        PostMethod postMethod = new PostMethod(url1);
        postMethod.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        postMethod.setRequestHeader("Upgrade-Insecure-Requests", "1");
        postMethod.setRequestHeader("Origin", "http://cx.ahzsks.cn");
        postMethod.setRequestHeader("Referer", "http://cx.ahzsks.cn/pugao/pglq2021_in-00b8bd3f-d5c8-4f90-b4e7-1b76cf147bbd.php");
        postMethod.setRequestHeader("Cookie", substring1);
        NameValuePair[] data = {new NameValuePair("ksh", "21341602130751"),
                new NameValuePair("sfzh", "341281200402236900"), new NameValuePair("yzm", substring)};
        postMethod.setRequestBody(data);
        try {
            httpClient.executeMethod(postMethod);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result1 = null;
        try {
            result1 = postMethod.getResponseBodyAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document parse = Jsoup.parse(result1);
        Element table_td = parse.select("table td").get(0);
        String s1 = table_td.toString();
        String ksh = s1.substring(s1.indexOf("考生号") + 3, s1.indexOf("考生号") + 17);
        String sfzh = s1.substring(s1.indexOf("身份证号：") + 5, s1.indexOf("身份证号：") + 23);
        Element table_td1 = parse.select("table td").get(1);
        String s2 = table_td1.toString();
        String lzjg = s2.substring(s2.indexOf("\">") + 2, s2.indexOf("</td>"));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(new Date());
        String resultDetails ="查询时间:"+format +"</br>"+ "姓名:老婆大人</br>"+"身份证号:" + sfzh + "</br>" + "考生号：" + ksh + "</br>" + "录取结果:" + lzjg+"</br>";
        return resultDetails;
    }
}
