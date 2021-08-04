package com.badu;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) throws IOException {
        //初始化clnt,使用单例方式
//        YunpianClient clnt = new YunpianClient("78607e9b798ed24c5effb3cc772cefa3").init();
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", "78607e9b798ed24c5effb3cc772cefa4");
        params.put("text", "测试");
        params.put("mobile", "19855113839");

        HttpClient httpClient = new HttpClient();
        NameValuePair[] nameValuePairs = {new NameValuePair("apikey", "78607e9b798ed24c5effb3cc772cefa3"),
        new NameValuePair("text", "测试"),new NameValuePair("mobile", "19855113839")};
        PostMethod postMethod = new PostMethod("https://sms.yunpian.com/v2/sms/single_send.json");
        postMethod.setRequestBody(nameValuePairs);
        postMethod.setRequestHeader("Accept","application/json;charset=utf-8");
        postMethod.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
        httpClient.executeMethod(postMethod);
        String responseBodyAsString = postMethod.getResponseBodyAsString();
        System.out.println(responseBodyAsString);
    }

}
