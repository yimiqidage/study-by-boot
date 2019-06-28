package com.test.httpclient;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.lang.StringUtils;

/**
 * 
* @classname TestHttps
* @description httpclient形式提交https地址(会自动加载证书，调用HttpsCertUtils)
* @author shiwei
* @date 2014-4-4 上午11:16:12
*
 */
public class TestHttps {

    /**
     * 默认UTF-8编码
     */
    private static final String ENCODING_DEFAULT="UTF-8";

    /**
     * 使用post方式提交到https地址
     * @title httpsPost
     * @param url
     * @param param
     * @return
     * @throws IOException
     * 参照地址：http://blog.csdn.net/pandazxx/article/details/1657109
     */
    public static String post(String url,String encoding,String param) {
        String str ="";
        if(StringUtils.isBlank(url) ){return str;}
        
        BufferedReader br = null;
        HttpsURLConnection conn = null;
        try {
            
            conn = HttpsCertUtils.getUrlConnection(url,param);
            
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            conn.connect();
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            out.writeBytes(param); //设置请求的参数
            out.flush();
            out.close();
            
            InputStream is =conn.getInputStream(); 
            char[] data = new char[1024]; 
            br = new BufferedReader(new InputStreamReader(is,encoding)); 
            str = String.valueOf(data, 0, br.read(data));
            br.close();
            is.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            
        }
        return str;
    }
    
    
    /**
     * 使用get方式提交https地址
     * @title httpsGet
     * @param url
     * @return
     * @throws IOException
     */
    public static String get(String url,String encoding,String params) {
        String str ="";
        if(StringUtils.isBlank(url) ){return str;}
        
        BufferedReader br = null;
        try {
            
            HttpsURLConnection conn = HttpsCertUtils.getUrlConnection(url,params);
            conn.setRequestProperty("Accept-Charset",encoding); 
            conn.setRequestMethod("GET"); 
            InputStream is =conn.getInputStream(); 
            char[] data = new char[1024]; 
            br = new BufferedReader(new InputStreamReader(is,encoding)); 
            str = String.valueOf(data, 0, br.read(data));
            br.close();
            is.close();
            
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            
            if(br !=null ){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return str;
    }

    /**
     * post方式，map参数，指定编码提交
     * @title post
     * @param url
     * @param encoding
     * @param params
     * @return
     */
    public static String post(String url,String encoding,Map<String,String>params){
        if(encoding==null || encoding.equals("")) encoding=ENCODING_DEFAULT;
        String content = map2Str(params);
        return post(url, encoding, content);
    }
    
    /**
     * get方式，map参数，指定编码提交
     * @title get
     * @param url
     * @param encoding
     * @param params
     * @return
     * @throws IOException 
     */
    public static String get(String url,String encoding,Map<String,String>params){
        if(encoding==null || encoding.equals("")) encoding=ENCODING_DEFAULT;
        String content = map2Str(params);
        return get(url, encoding, content);
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
    
    public static void main(String[] args) {
      
//        String params = "notify_data=<notify><payment_type>1</payment_type><subject>网络课堂（订单id:5955056）</subject><trade_no>2014040343350618</trade_no><buyer_email>shiwei8b402@126.com</buyer_email><gmt_create>2014-04-03 13:59:51</gmt_create><notify_type>trade_status_sync</notify_type><quantity>1</quantity><out_trade_no>5955056</out_trade_no><notify_time>2014-04-03 13:59:56</notify_time><seller_id>2088001341855672</seller_id><trade_status>TRADE_FINISHED</trade_status><is_total_fee_adjust>N</is_total_fee_adjust><total_fee>0.01</total_fee><gmt_payment>2014-04-03 13:59:55</gmt_payment><seller_email>koolearn@126.com</seller_email><gmt_close>2014-04-03 13:59:55</gmt_close><price>0.01</price><buyer_id>2088102194056187</buyer_id><notify_id>3a2b30408343b9244f8f42dbd6b3bf2130</notify_id><use_coupon>N</use_coupon></notify>&sec_id=MD5&service=alipay.wap.trade.create.direct&sign=79f17c00a05132e97b23a1dbb9649e48&v=1.0";
//        String url="http://wap.neibu.koolearn.com/pay/alipay_wap/notice/notify";
//        String s =  post(url, null,params);
//        System.out.println("TestHttp return :"+s);
        
        String url =  "https://mapi.alipay.com/gateway.do";
        String params = "service=notify_verify&partner=2088001341855672&notify_id=3a2b30408343b9244f8f42dbd6b3bf2130";
        String s =  get(url, "UTF-8", params);
        System.out.println(s);
    }
}
