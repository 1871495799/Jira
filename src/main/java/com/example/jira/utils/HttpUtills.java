package com.example.jira.utils;

import com.example.jira.model.AzureProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description : XXXX
 * Author by zengh17
 * Date on 2020/3/31 13:19
 */
@Component
public class HttpUtills {

    private static final Logger log = LoggerFactory.getLogger(HttpUtills.class);

//    private static String authorization;
//    @Value("${authorization}")
//    public void setAuthorization(String authorization){
//        this.authorization=authorization;
//    }


    private static String authorization="Basic RnVsbCBhY2Nlc3MgdG9rZW46dW83NzdjaXlqZWYyaGJyd3M2eGd4ZGE2aWNsaGJ3bDJvNnl5ZnRibTJ3NXBkdHczN2F3YQ==";

    @Bean
    public static RestTemplate restTemplate() {
        //复杂构造函数的使用
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(120000);// 设置超时
        requestFactory.setReadTimeout(50000);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(requestFactory);
        return restTemplate;
    }


    /***
     * HTTP的get请求，返回请求体
     * @return
     */
    public static String get(String url)  {
        log.info("HTTP的url ："+url);
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        log.info("HTTP的resEntity.getStatusCodeValue()请求结果："+resEntity.getStatusCodeValue());
        if(resEntity.getStatusCodeValue()==200){
            String body = resEntity.getBody();
            log.info("HTTP的get请求结果："+body);
            return body;
        }else {
            throw new RuntimeException("调用微软接口---"+url+"--异常了");
        }
    }



    /***
     * HTTP的get请求，返回请求体
     * @return
     */
    public static List<String> getMore(String url)  {
        log.info("HTTP的url ："+url);
        List<String> list=new ArrayList<>();
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        log.info("HTTP的resEntity.getStatusCodeValue()请求结果："+resEntity.getStatusCodeValue());
        if(resEntity.getStatusCodeValue()==200){
            HttpHeaders header = resEntity.getHeaders();
            List<String> value=header.get("x-ms-continuationtoken");
            String body = resEntity.getBody();
            log.info("HTTP的get请求body结果："+body);
            if(value !=null){
                list.add(value.get(0));
            };
            list.add(body);
            return list;
        }else {
            throw new RuntimeException("调用微软接口---"+url+"--异常了");
        }
    }


    /***
     * HTTP的get请求，返回请求体
     * @return
     */
    public static String get(String url,String authorizatio)  {
        log.info("HTTP的url ："+url);
        RestTemplate restTemplate = restTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorizatio);
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        log.info("HTTP的resEntity.getStatusCodeValue()请求结果："+resEntity.getStatusCodeValue());
        if(resEntity.getStatusCodeValue()==200){
            String body = resEntity.getBody();
            log.info("HTTP的get请求结果："+body);
            return body;
        }else {
            throw new RuntimeException("调用微软接口---"+url+"--异常了");
        }
    }



    /***
     * HTTP的post请求，返回请求体
     * @return
     */
    public static String post(String url,String query) {
        log.info("HTTP的url ："+url+"，HTTP的query ："+query);
        String res=null;
        Map<String, String> params = new HashMap<>();
        params.put("query",query);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getStatusCode() + " | " + response.getBody());
        if(response.getStatusCode()==HttpStatus.OK){
            res=response.getBody();
        }
        return res;
    }



    /***
     * HTTP的post请求，返回请求体
     * @return
     */
    public static String postJson(String url,String json) throws Exception {
        String res=null;
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getStatusCode() + " | " + response.getBody());
        if(response.getStatusCode()==HttpStatus.OK){
            res=response.getBody();
        }
        return res;
    }



    /***
     * HTTP的post请求，返回请求体
     * @return
     */
    public static String post(String url,String query,String authorization) throws Exception {
        log.info("HTTP的url ："+url+"，HTTP的query ："+query);
        String res=null;
        Map<String, String> params = new HashMap<>();
        params.put("query",query);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authorization);
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        HttpEntity<Map<String, String>> request = new HttpEntity<>(params, headers);
        RestTemplate restTemplate = restTemplate();
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
        System.out.println(response.getStatusCode() + " | " + response.getBody());
        if(response.getStatusCode()==HttpStatus.OK){
            res=response.getBody();
        }
        return res;
    }



    /**
     * 截取字符串str中指定字符 strStart、strEnd之间的字符串
     *
     * @param str
     * @param strStart
     * @param strEnd
     * @return
     */
    public static String subStringTool(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }




    /**
     * 正则表达式用于匹配字符串内容
     * @param regEx
     * @param string
     * @return
     */
    public static List<String> regEx(String regEx,String string) {
        List<String> list= new ArrayList<>();
        Matcher m = Pattern.compile(regEx).matcher(string);
        while (m.find()) {
            list.add(m.group());
        }
        System.out.println("匹配规则为： "+regEx+"，匹配内容为： "+string+"，匹配结果： " + list.toString());
        return list;
    }


    /**
     * 正则表达式用于匹配字符串内容--具体到匹配到那一组的信息---非贪婪模式
     * @param regEx
     * @param string
     * @return
     */
    public static List<String> regEx(String regEx,String string,int x) {
        List<String> list= new ArrayList<>();
        Matcher m = Pattern.compile(regEx).matcher(string);
        while (m.find()) {
            list.add(m.group(x));
        }
        log.info("匹配规则为： "+regEx+"，字符串为： "+string+"，匹配结果： " + list.toString());
        return list;
    }


    /**
     * 正则表达式用于匹配字符串内容--具体到匹配到那一组的信息---非贪婪模式
     * @param regEx
     * @param string
     * @return
     */
    public static String regExString(String regEx,String string) {
        String re = null;
        Matcher m = Pattern.compile(regEx).matcher(string);
        while (m.find()) {
            re = m.group();
        }
        return re ;
    }

    /**
     * 正则表达式用于匹配字符串内容--具体到匹配到那一组的信息---贪婪模式
     * @param regEx
     * @param string
     * @return
     */
    public static List<String> regExList(String regEx,String string) {
        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile(regEx).matcher(string);
        while (m.find()) {
            list.add(m.group());
        }
        return list ;
    }



    public static void main(String[] args) {

    }
}
