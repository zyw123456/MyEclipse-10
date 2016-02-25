package com.sinoway.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeoutException;

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
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

/**
 * http
 * @author jintao
 *
 * @date 2016-1-14 上午11:17:51
 */
public class HttpsUtils {
	
	   private static Map<String, String> headers = new HashMap<String, String>();  
	    static {  
//	        headers.put("User-Agent",  
//	                "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");  
	        headers.put("Accept-Language", "en-US,en;q=0.8");  
	        headers.put("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.3");  
	        headers.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
	        headers.put("Content-Type", "application/x-www-form-urlencoded");  
	        headers.put("Accept-Encoding", "gzip,deflate,sdch");  
	    }
	
	@SuppressWarnings("deprecation")
	public static String sendPostSSLRequest(String reqURL, Map<String, String> params, String encodeCharset,int connTimeOut,int socketTimeOut) throws ConnectTimeoutException{ 
        String responseContent = "通信失败"; 
        HttpClient httpClient = new DefaultHttpClient(); 
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connTimeOut); 
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, socketTimeOut); 
        //创建TrustManager() 
        //用于解决javax.net.ssl.SSLPeerUnverifiedException: peer not authenticated 
        X509TrustManager trustManager = new X509TrustManager(){ 
            @Override 
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            @Override 
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {} 
            @Override 
            public X509Certificate[] getAcceptedIssuers() {return null;} 
        }; 
        //创建HostnameVerifier 
        //用于解决javax.net.ssl.SSLException: hostname in certificate didn't match: <123.125.97.66> != <123.125.97.241> 
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
            //TLS1.0与SSL3.0基本上没有太大的差别,可粗略理解为TLS是SSL的继承者，但它们使用的是相同的SSLContext 
            SSLContext sslContext = SSLContext.getInstance(SSLSocketFactory.TLS); 
            //使用TrustManager来初始化该上下文,TrustManager只是被SSL的Socket所使用 
            sslContext.init(null, new TrustManager[]{trustManager}, null); 
            //创建SSLSocketFactory 
            SSLSocketFactory socketFactory = new SSLSocketFactory(sslContext, hostnameVerifier); 
            //通过SchemeRegistry将SSLSocketFactory注册到HttpClient上 
            httpClient.getConnectionManager().getSchemeRegistry().register(new Scheme("https", 443, socketFactory)); 
            //创建HttpPost 
            HttpPost httpPost = new HttpPost(reqURL); 
            //由于下面使用的是new UrlEncodedFormEntity(....),所以这里不需要手工指定CONTENT_TYPE为application/x-www-form-urlencoded 
            //因为在查看了HttpClient的源码后发现,UrlEncodedFormEntity所采用的默认CONTENT_TYPE就是application/x-www-form-urlencoded 
            //httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=" + encodeCharset); 
            //构建POST请求的表单参数 
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
            
            if(response.getStatusLine().getStatusCode() == 200){
            	if (null != entity) { 
            		responseContent = EntityUtils.toString(entity, ContentType.getOrDefault(entity).getCharset()); 
            		EntityUtils.consume(entity); 
            	}
            }else {
            	throw new ConnectTimeoutException();
            }
        } catch (ConnectTimeoutException cte){ 
            cte.printStackTrace();
            throw new ConnectTimeoutException(cte.getMessage());
        } catch (SocketTimeoutException ste){ 
            ste.printStackTrace();
            
            //LogUtil.getLogger().error("请求通信[" + reqURL + "]时读取超时,堆栈轨迹如下", ste); 
        } catch (Exception e) { 
            e.printStackTrace();
           // LogUtil.getLogger().error("请求通信[" + reqURL + "]时偶遇异常,堆栈轨迹如下", e); 
        } finally { 
            httpClient.getConnectionManager().shutdown(); 
        } 
        return responseContent; 
    } 
	
	
	 /**  
     * 异常或者没拿到返回结果的情况下,result为""  
     *   
     * @param url  
     * @param param  
     * @return  
	 * @throws ConnectTimeoutException 
     */  
    @SuppressWarnings("deprecation")
	public static String httpPost(String url, Map<String, String> params,int connTimeOut,int socketTimeOut) throws ConnectTimeoutException {  
//        logger.info("httpPost URL [" + url + "] start ");  
        DefaultHttpClient httpclient = null;  
        HttpPost httpPost = null;  
        HttpResponse response = null;  
        HttpEntity entity = null;  
        String result = "";  
        StringBuffer suf = new StringBuffer();  
        try {  
            httpclient = new DefaultHttpClient();  
            // 设置cookie的兼容性---考虑是否需要  
//            httpclient.getParams().setParameter(ClientPNames.COOKIE_POLICY,  
//                    CookiePolicy.BROWSER_COMPATIBILITY);  
            httpPost = new HttpPost(url);  
            // 设置各种头信息  
            for (Entry<String, String> entry : headers.entrySet()) {  
                httpPost.setHeader(entry.getKey(), entry.getValue());  
            }  
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
            // 传入各种参数  
            if (null != params) {  
                for (Entry<String, String> set : params.entrySet()) {  
                    String key = set.getKey();  
                    String value = set.getValue() == null ? "" : set.getValue()  
                            .toString();  
                    nvps.add(new BasicNameValuePair(key, value));  
                    suf.append(" [" + key + "-" + value + "] ");  
                }  
            }  
//            logger.info("param " + suf.toString());  
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));  
            // 设置连接超时时间  
            HttpConnectionParams.setConnectionTimeout(httpclient.getParams(),  
                    5000);  
            // 设置读数据超时时间  
            HttpConnectionParams.setSoTimeout(httpPost.getParams(),  
                    5000);  
            response = httpclient.execute(httpPost);  
            int statusCode = response.getStatusLine().getStatusCode();  
            if (statusCode != HttpStatus.SC_OK) {  
//                logger.error("HttpStatus ERROR" + "Method failed: "  
//                        + response.getStatusLine());  
                System.out.println(response.getStatusLine());
                return "";  
            } else {  
                entity = response.getEntity();  
                if (null != entity) {  
                    byte[] bytes = EntityUtils.toByteArray(entity);  
                    result = new String(bytes, "utf-8");  
                } else {  
//                    logger.error("httpPost URL [" + url  
//                            + "],httpEntity is null.");  
                	throw new ConnectTimeoutException();
                }  
                return result;  
            }  
            
        }catch (ConnectTimeoutException e) {
			// TODO: handle exception
        	e.printStackTrace();
            throw new ConnectTimeoutException(e.getMessage());
		} 
        catch (SocketTimeoutException e){ 
        	e.printStackTrace();
            return ""; 
        }
        catch (Exception e) {  
//            logger.error("httpPost URL [" + url + "] error, ", e);  
        	e.printStackTrace();
            return "";  
        } finally {  
            if (null != httpclient) {  
                httpclient.getConnectionManager().shutdown();  
            }  
//            logger.info("RESULT:  [" + result + "]");  
//            logger.info("httpPost URL [" + url + "] end ");  
        }  
    }
    
	public static String executeGet(String url,int connTimeOut,int socketTimeOut) throws ConnectTimeoutException  {  
        BufferedReader in = null;  
  
        String content = null;  
        try {  
            // 定义HttpClient  
        	CloseableHttpClient client = HttpClients.createDefault();
            
            // 实例化HTTP方法  
            HttpGet request = new HttpGet(); 
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connTimeOut).build();
            request.setConfig(requestConfig);
            request.setURI(new URI(url));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent(),"utf-8"));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
//            in.close();  
            content = sb.toString();  
//            return content;  
        }catch (ConnectTimeoutException e) {
        	throw new ConnectTimeoutException();
		}
        
        catch(Exception e){
        	e.printStackTrace();
        }finally{
                 if (in != null) {  
                     try {  
                         in.close();// 最后要关闭BufferedReader  
                     } catch (Exception e) {  
                         e.printStackTrace();  
                     }  
                 }  
        }
		return content;
       
    }  
	
	
	
	
	public static String executeGet(String url,String charset,int connTimeOut,int socketTimeOut) throws ConnectTimeoutException  {  
        BufferedReader in = null;  
  
        String content = null;  
        try {  
            // 定义HttpClient  
        	CloseableHttpClient client = HttpClients.createDefault();
            
            // 实例化HTTP方法  
            HttpGet request = new HttpGet(); 
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connTimeOut).build();
            request.setConfig(requestConfig);
            request.setURI(new URI(url));  
            HttpResponse response = client.execute(request);  
  
            in = new BufferedReader(new InputStreamReader(response.getEntity()  
                    .getContent(),charset));  
            StringBuffer sb = new StringBuffer("");  
            String line = "";  
            String NL = System.getProperty("line.separator");  
            while ((line = in.readLine()) != null) {  
                sb.append(line + NL);  
            }  
//            in.close();  
            content = sb.toString();  
//            return content;  
        }catch (ConnectTimeoutException e) {
        	throw new ConnectTimeoutException();
		}
        
        catch(Exception e){
        	e.printStackTrace();
        }finally{
                 if (in != null) {  
                     try {  
                         in.close();// 最后要关闭BufferedReader  
                     } catch (Exception e) {  
                         e.printStackTrace();  
                     }  
                 }  
        }
		return content;
       
    }  
	
	
	public static void main(String[] args) throws ConnectTimeoutException {
		String executeGet = executeGet("http://master:8080/sinoway_mcp/httpControl",10000,60000);
		System.out.println(executeGet);
	}
	
	

}
