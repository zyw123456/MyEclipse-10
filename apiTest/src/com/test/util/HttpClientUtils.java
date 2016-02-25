package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.Iterator;
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
  
import org.apache.http.HttpEntity;  
import org.apache.http.HttpResponse;  
import org.apache.http.HttpStatus;  
import org.apache.http.NameValuePair;  
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;  
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;  
import org.apache.http.client.params.ClientPNames;  
import org.apache.http.client.params.CookiePolicy;  
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;  
import org.apache.http.message.BasicNameValuePair;  
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;  
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;  
import org.apache.log4j.Logger;  
  
  
/** 
 *  
 * httpclient����http�ӿڵĹ����� 
 *  
 * 
 *  
 */  
public class HttpClientUtils {  
  
    public static final Logger logger = Logger.getLogger(HttpClientUtils.class);  
    private static Map<String, String> headers = new HashMap<String, String>();  
    static {  
//        headers.put("User-Agent",  
//                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");  
        headers.put("Accept-Language", "en-US,en;q=0.8");  
        headers.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");  
        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
        headers.put("Content-Type", "application/x-www-form-urlencoded");  
        headers.put("Accept-Encoding", "gzip,deflate,sdch");  
    }  
    
	public static String executeGet(String url)  {  
        BufferedReader in = null;  
  
        String content = null;  
        try {  
            // ����HttpClient  
            HttpClient client = new DefaultHttpClient();  
            // ʵ����HTTP����  
            HttpGet request = new HttpGet();  
            request.setURI(new URI(url));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent()));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
//            in.close();  
            content = sb.toString();  
//            return content;  
        }catch(Exception e){
        	e.printStackTrace();
        }finally{
                 if (in != null) {  
                     try {  
                         in.close();// ���Ҫ�ر�BufferedReader  
                     } catch (Exception e) {  
                         e.printStackTrace();  
                     }  
                 }  
        }
		return content;
       
    }  
  
  
    /**  
     * �쳣����û�õ����ؽ���������,resultΪ""  
     *   
     * @param url  
     * @param param  
     * @return  
     */  
    public static String httpPost(String url, Map<String, String> params) {  
        logger.info("httpPost URL [" + url + "] start ");  
        DefaultHttpClient httpclient = null;  
        HttpPost httpPost = null;  
        HttpResponse response = null;  
        HttpEntity entity = null;  
        String result = "";  
        StringBuffer suf = new StringBuffer();  
        try {  
            httpclient = new DefaultHttpClient();  
            // ����cookie�ļ�����---�����Ƿ���Ҫ  
//            httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY,  
//                    CookiePolicy.BROWSER_COMPATIBILITY);  
            httpPost = new HttpPost(url);  
            // ���ø���ͷ��Ϣ  
            for (Entry<String, String> entry : headers.entrySet()) {  
                httpPost.setHeader(entry.getKey(), entry.getValue());  
            }  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            // ������ֲ���  
            if (null != params) {  
                for (Entry<String, String> set : params.entrySet()) {  
                    String key = set.getKey();  
                    String value = set.getValue() == null ? "" : set.getValue()  
                            .toString();  
                    nvps.add(new BasicNameValuePair(key, value));  
                    suf.append(" [" + key + "-" + value + "] ");  
                }  
            }  
            logger.info("param " + suf.toString());  
            System.out.println("param" + suf.toString());
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
            // �������ӳ�ʱʱ��  
            HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),  
                    5000);  
            // ���ö����ݳ�ʱʱ��  
            HttpConnectionParams.setSoTimeout(httpPost.getParams(),  
                    5000);  
            response = httpclient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
                logger.error("HttpStatus ERROR" + "Method failed: "  
                        + response.getStatusLine());  
                System.out.println(response.getStatusLine());
                return "";  
            } else {  
                entity = response.getEntity();  
                if (null != entity) {  
                    byte[] bytes = EntityUtils.toByteArray(entity);  
                    result = new String(bytes, "utf-8");  
                } else {  
                    logger.error("httpPost URL [" + url  
                            + "],httpEntity is null.");  
                }  
                return result;  
            }  
        } catch (Exception e) {  
            logger.error("httpPost URL [" + url + "] error, ", e);  
            return "";  
        } finally {  
            if (null != httpclient) {  
                httpclient.getConnectionManager().shutdown();  
            }  
            logger.info("RESULT:  [" + result + "]");  
            logger.info("httpPost URL [" + url + "] end ");  
        }  
    }
    
    public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset){ 
        String responseContent = "ͨ��ʧ��"; 
        HttpClient httpClient = new DefaultHttpClient(); 
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000); 
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 20000); 
        //����TrustManager() 
        //���ڽ��javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated 
        X509TrustManager trustManager = new X509TrustManager(){ 
            @Override 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            @Override 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            @Override 
            public X509Certificate[] getAcceptedIssuers() {return null;} 
        }; 
        //����HostnameVerifier 
        //���ڽ��javax.net.ssl.SSLException: hostname in certificate didn't match: <123.125.97.66> != <123.125.97.241> 
        X509HostnameVerifier hostnameVerifier = new X509HostnameVerifier(){ 
            @Override 
            public void verify(String host, SSLSocket ssl) throws IOException {} 
            @Override 
            public void verify(String host, X509Certificate cert) throws SSLException {} 
            @Override 
            public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {} 
            @Override 
            public boolean verify(String arg0, SSLSession arg1) {return true;} 
        }; 
        try { 
            //TLS1.0��SSL3.0������û��̫��Ĳ��,�ɴ������ΪTLS��SSL�ļ̳��ߣ�������ʹ�õ�����ͬ��SSLContext 
            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS); 
            //ʹ��TrustManager����ʼ����������,TrustManagerֻ�Ǳ�SSL��Socket��ʹ�� 
            sslContext.init(null, new TrustManager[]{trustManager}, null); 
            //����SSLSocketFactory 
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier); 
            //ͨ��SchemeRegistry��SSLSocketFactoryע�ᵽHttpClient�� 
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory)); 
            //����HttpPost 
            HttpPost httpPost = new HttpPost(reqURL); 
            //��������ʹ�õ���new UrlEncodedFormEntity(....),�������ﲻ��Ҫ�ֹ�ָ��CONTENT_TYPEΪapplication/x-www-form-urlencoded 
            //��Ϊ�ڲ鿴��HttpClient��Դ�����,UrlEncodedFormEntity�����õ�Ĭ��CONTENT_TYPE����application/x-www-form-urlencoded 
            //httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset); 
            //����POST����ı����� 
            if(null != params){ 
                List<NameValuePair> formParams = new ArrayList<NameValuePair>(); 
                for(Map.Entry<String,String> entry : params.entrySet()){ 
                    formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue())); 
                } 
                httpPost.setEntity(new UrlEncodedFormEntity(formParams, encodeCharset)); 
//                httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
            } 
            HttpResponse response = httpClient.execute(httpPost); 
            HttpEntity entity = response.getEntity(); 
            if (null != entity) { 
                responseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset()); 
                EntityUtils.consume(entity); 
            } 
        } catch (ConnectTimeoutException cte){ 
            cte.printStackTrace();
        } catch (SocketTimeoutException ste){ 
            ste.printStackTrace();
            //LogUtil.getLogger().error("����ͨ��[" + reqURL + "]ʱ��ȡ��ʱ,��ջ�켣����", ste); 
        } catch (Exception e) { 
            e.printStackTrace();
           // LogUtil.getLogger().error("����ͨ��[" + reqURL + "]ʱż���쳣,��ջ�켣����", e); 
        } finally { 
            httpClient.getConnectionManager().shutdown(); 
        } 
        return responseContent; 
    } 
    
    
    
}  