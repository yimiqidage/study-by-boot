package com.test.httpclient;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * https 证书加载
* @classname HttpsCertUtils
* @description 
* @author shiwei
* @date 2014-04-02 下午3:32:11
*
 */
public class HttpsCertUtils implements X509TrustManager {
    
    private X509TrustManager utils ;
    
    public static HttpsURLConnection getUrlConnection(String strUrl,String param)
             {
        try{
         // 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = { new HttpsCertUtils() };
        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();
        // 创建URL对象
        URL myURL = new URL(strUrl + "?" +param);
        // 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
        HttpsURLConnection httpsConn = (HttpsURLConnection) myURL.openConnection();
        httpsConn.setSSLSocketFactory(ssf);
        
        return httpsConn;
        
       
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
        
        
    }
    
    private static File getSystemKeystoreFile()
    {
        char sep = File.separatorChar;
        File dir = new File(System.getProperty("java.home") + sep + "lib" + sep + "security");
        File file = new File(dir, "jssecacerts");
        if (file.isFile() == false) {
            file = new File(dir, "cacerts");
        }
        return file;
    }
    
    private  HttpsCertUtils() throws Exception {
        // create a "default" JSSE X509TrustManager.
        KeyStore ks = KeyStore.getInstance("JKS");
        ks.load(new FileInputStream(getSystemKeystoreFile()),
           null);
        TrustManagerFactory tmf =
        TrustManagerFactory.getInstance("SunX509", "SunJSSE");
        tmf.init(ks);
        TrustManager tms [] = tmf.getTrustManagers();
        for (int i = 0; i < tms.length; i++) {
            if (tms[i] instanceof X509TrustManager) {
                utils = (X509TrustManager) tms[i];
                return;
            }
        }
        throw new Exception("Couldn't initialize");
    }

    public void checkClientTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        try {
            utils.checkClientTrusted(arg0, arg1);
        } catch (CertificateException excep) {
        }
        
    }

    public void checkServerTrusted(X509Certificate[] arg0, String arg1)
            throws CertificateException {
        try {
            utils.checkServerTrusted(arg0, arg1);
        } catch (CertificateException excep) {
        }
        
    }

    public X509Certificate[] getAcceptedIssuers() {
         return utils.getAcceptedIssuers();
    }




}
