package com.test.httpclient;

import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;



/**
* @classname TestHttp
* @description httpclient方式提交参数到指定url地址
* @author shiwei
* @date 2014-4-4 上午11:05:04
*
*/ 
public class TestHttp {

    private static final String DEFAULT_ENCODING="UTF-8";
    public static String encoding;

    private static final HttpConnectionManager connectionManager;
    
    private static final HttpClient client;

    static {

        HttpConnectionManagerParams params = loadHttpConfFromFile();
        
        connectionManager = new MultiThreadedHttpConnectionManager();

        connectionManager.setParams(params);

        
        client = new HttpClient(connectionManager);
    }
    
    private static HttpConnectionManagerParams loadHttpConfFromFile(){
        
        encoding = "UTF-8";
        
        HttpConnectionManagerParams params = new HttpConnectionManagerParams();
        params.setConnectionTimeout(15000);
        params.setSoTimeout(30000);
        params.setStaleCheckingEnabled(true);
        params.setTcpNoDelay(true);
        params.setDefaultMaxConnectionsPerHost(100);
        params.setMaxTotalConnections(1000);
        params.setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        return params;
    }
    
    /**
     * post方式、指定编码、提交字符串拼接参数( a=1&b=2 )格式
     * @title post
     * @param url
     * @param encoding
     * @param content
     * @return
     */
    public static String post(String url, String encoding, String content) {
        try {
            byte[] resp = post(url, content.getBytes(encoding));
            if (null == resp)
                return null;
            return new String(resp, encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * post方式，默认编码(UTF-8)、提交拼接字符串参数( a=1&b=2 )格式
     * @title post
     * @param url
     * @param content
     * @return
     */
    public static String post(String url, String content) {
        return post(url, encoding, content);
    }
    
    /**
     * post方式,指定编码(默认为UTF-8)、提交map格式参数
     * @title post
     * @param url
     * @param encoding
     * @param params
     * @return
     */
    public static String post(String url, String encoding,Map<String,String>params){
        
        String content = map2Str(params);
        if(encoding==null || encoding.equals("")) encoding="UTF-8";
        return post(url, encoding, content);
    }

    /**
     * 使用get方式提交
     * @title get
     * @param url
     * @param encoding
     * @return
     */
    public static String get(String url,String encoding)  {
        
        if (encoding==null || encoding.equals("")) encoding=DEFAULT_ENCODING;
        
        GetMethod method = new GetMethod(url);
        method.addRequestHeader("Connection", "Keep-Alive");
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
        
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            byte[] bs = method.getResponseBody();
            
            return new String(bs,encoding);

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            method.releaseConnection();
        }
    }
    
    /**
     * get方式，提交map参数
     * @title get
     * @param url
     * @param encoding
     * @param map
     * @return
     */
    public static String get(String url,String encoding,Map<String,String>map)  {
        String content = map2Str(map);
        return get(url+"?"+content, encoding);
    }
    
    /**
     * map 转换成 str
     * @title map2Str
     * @param params
     * @return
     */
    public static String map2Str(Map<String,String>params){
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }
        return prestr;
    }

    private static byte[] post(String url, byte[] content) {
        try {
            byte[] ret = post(url, new ByteArrayRequestEntity(content));
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static byte[] post(String url, RequestEntity requestEntity) throws Exception {

        PostMethod method = new PostMethod(url);
        method.addRequestHeader("Connection", "Keep-Alive");
        method.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
        method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        method.setRequestEntity(requestEntity);
        method.addRequestHeader("Content-Type","application/x-www-form-urlencoded");
        
        try {
            int statusCode = client.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            return method.getResponseBody();

        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            method.releaseConnection();
        }
    }

    public static void main(String[] args) {
        Map<String,String> param = new HashMap<String, String>();
        param.put("region", "北京");
        param.put("mobilephone", "15300308509");
        param.put("phone", "15300308509");
        param.put("address", "北京昌平区六环以内北京昌平区回龙观龙锦苑东二区16-4-601");
        param.put("role", "2");
        param.put("userName", "koo_2qcee");
        param.put("channel", "JD");
        param.put("password", "password");
        
        
        String url="http://login.neibu.koolearn.com/sso/mobileRegister.do";
        String s =  post(url, map2Str(param));
        System.out.println("TestHttp return :"+s);
    }
}
