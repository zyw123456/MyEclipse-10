package com.sinoway.common.service.impl;


import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.CharsetUtils;
import org.apache.http.util.EntityUtils;

import com.sinoway.common.entity.HttpCommonEntity;
import com.sinoway.common.entity.HttpDataEntity;
import com.sinoway.common.entity.HttpResponseCommonEntity;
import com.sinoway.common.exception.HttpException;
import com.sinoway.common.service.IHttpProviderService;
import com.sinoway.common.util.Constant.HttpMethod;
import com.sinoway.common.util.Constant.HttpParamType;
import com.sinoway.common.util.IOStreamUtil;
import com.yzj.wf.common.WFLogger;


/**
 * HttpService 通用HttpProvider接口调用实现
 * @author miles
 *
 */
public class HttpProviderServiceImpl implements IHttpProviderService{

	
	private static final WFLogger logger = WFLogger.getLogger(HttpProviderServiceImpl.class);
	private CloseableHttpResponse resp = null;
	private CloseableHttpClient httpClient = null;
	
	/**
	 * 通用核心请求
	 */
	public HttpCommonEntity httpCommunicate(HttpCommonEntity entity) throws Exception {
	 try{
		HttpResponseCommonEntity response = null;
		if(HttpMethod.HTTP_METHOD_POST.getCode().equals(entity.getMethod())){
//			response = httpPostProvider(entity);
			response = httpPostWithGetProvider(entity);
		}else if(HttpMethod.HTTP_METHOD_GET.getCode().equals(entity.getMethod())){
			response = httpGetprovider(entity);
		}else {
			throw new  HttpException("请求方法未确定");
		}
		
		if(response == null){
		    throw new  HttpException("核心返回的信息为空");
		}
		entity.setResponse(response);
	 }catch(Exception e){
		e.printStackTrace();
		throw e;
	 }
		return entity;
	}

	/**
	 * 通用HTTP Get请求
	 * @param entity
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 * @throws HttpException 
	 * @throws URISyntaxException 
	 */
	private HttpResponseCommonEntity httpGetprovider(HttpCommonEntity entity) throws ParseException, IOException, HttpException, URISyntaxException {
		String url  = entity.getUrl();
		if (StringUtils.isBlank(url)) {
			throw new HttpException("请求地址未定义");
		}
		String res = null;
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//参数传递
		List<HttpDataEntity> params = entity.getParams();
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		List<Header> headers = new ArrayList<Header>();
		int paramSize = params.size();
		if(paramSize > 0){
			String key = null;
			Object value = null;
			String type = null;
			for(HttpDataEntity data : params){
				key = data.getKey();
				value = data.getValue();
				type = data.getType();
				if(HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode().equals(type)){
					paramList.add(new BasicNameValuePair(key, String.valueOf(value)));
				}else if(HttpParamType.PARAMTER_TYPE_STREAM.getCode().equals(type)){
					throw new HttpException("get方法调用不支持传送文件参数");
				}else if(HttpParamType.PARAMETER_TYPE_HEADER.getCode().equals(type)){
					headers.add(new BasicHeader(key, String.valueOf(value)));
				}else{
					throw new HttpException("传递参数类型未知, type: "+type);
				}
			}
		}
		// TODO URI 中文传参
		URI uri = new URIBuilder(url).
						setParameters(paramList).build();
        HttpGet httpget = new HttpGet(uri);  
        
        //配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom() 
                .setConnectionRequestTimeout((int) entity.getRequesttimeout())
                .setConnectTimeout((int) entity.getTimeout()) 
                .setSocketTimeout((int) entity.getSotimeout()).build(); 
        httpget.setConfig(requestConfig);
        
        //Header参数
        httpget.setHeaders((Header[]) headers.toArray());
       
        CloseableHttpResponse resp = httpClient.execute(httpget);  
        //新建返回对象
        HttpResponseCommonEntity resCommonEntity = new HttpResponseCommonEntity();

        try {
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = resp.getEntity();
				if (resEntity != null) {
					res = EntityUtils.toString(resEntity, Consts.UTF_8);
				}
				
				resCommonEntity.setReturnObj(res);
				resCommonEntity.setReponse(resp);;
				// 销毁
				EntityUtils.consume(resEntity);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpException(url);
		} 
		return resCommonEntity;
	}

	/**
	 * 通用Http Post提交带文件上传
	 * @param entity
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws HttpException 
	 */
	public Object httpPostProvider(HttpCommonEntity entity) throws ClientProtocolException, IOException, HttpException{
		String url  = entity.getUrl();
		if (StringUtils.isBlank(url)) {
			throw new HttpException("请求地址未定义");
		}
		String res = null;
		
		
		// 创建一个默认对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(url);
		RequestConfig requestConfig = RequestConfig.custom().
				setConnectionRequestTimeout((int) entity.getRequesttimeout()).//设置请求连接超时时间
				setSocketTimeout((int) entity.getSotimeout()).//设置传输超时时间
				setConnectTimeout((int)entity.getTimeout()).build();//设置连接超时时间

		// 建立文件参数
		MultipartEntityBuilder build = MultipartEntityBuilder.create();
		ContentType contentType = ContentType.create("text/plain", Consts.UTF_8);
		
		//参数传递
		List<HttpDataEntity> params = entity.getParams();
		int paramSize = params.size();
		if(paramSize > 0){
			String key = null;
			Object value = null;
			String type = null;
			//根据类型判断是form字段还是流
			for(HttpDataEntity data : params){
				key = data.getKey();
				value = data.getValue();
				type = data.getType();
				if(StringUtils.isNotBlank(key) && StringUtils.isNotBlank(type)){
					if(HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode().equals(type)){
						StringBody stringBody = new StringBody(String.valueOf(value), contentType);
						build.addPart(key, stringBody);// 设置请求参数
					}else if(HttpParamType.PARAMTER_TYPE_STREAM.getCode().equals(type)){
						File file = new File(String.valueOf(data.getValue()));
						build.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
						build.setCharset(CharsetUtils.get("UTF-8")).build();
						build.addBinaryBody(key, file);
						continue;
					}else if(HttpParamType.PARAMETER_TYPE_HEADER.getCode().equals(type)){
						continue;
					}else{
						throw new HttpException("传递参数类型未知, type: "+type);
					}
				}else{
					throw new HttpException("传递的参数键或者参数类型为空, key: "+ key +", value: " + value);
				}
			}
		}
		
		HttpEntity reqEntity = build.build();// 生成 HTTP POST 实体
		httppost.setEntity(reqEntity);// 设置请求参数
		httppost.setConfig(requestConfig);//设置参数配置
logger.debug("HTTP请求核心系统......." + entity.toString());			
		CloseableHttpResponse resp =  httpClient.execute(httppost);
		try {
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = resp.getEntity();
				
				if (resEntity != null) {
					res = EntityUtils.toString(resEntity, Charset.forName("UTF-8"));
				}
				// 销毁
				EntityUtils.consume(resEntity);
			}
logger.debug("接收核心系统返回报文:" + res);			
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpException(url);
		} finally {
			resp.close();
			httpClient.close();
		}
		return res;
		
	}
	
	
	/**
	 * 使用post提交url格式和get并存,格式: http://host:port/path/pathmethod?parama=a&paramb=b
	 * 当HttpDataEntity有值时,若参数名相同则会覆盖url的参数
	 * @param entity
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws HttpException
	 * @throws URISyntaxException 
	 */
	private HttpResponseCommonEntity httpPostWithGetProvider(HttpCommonEntity entity) throws ClientProtocolException, IOException, HttpException, URISyntaxException{
		String url  = entity.getUrl();
		if (StringUtils.isBlank(url)) {
			throw new HttpException("请求地址未定义");
		}
		String res = null;
		
		
		// 创建一个默认对象
		httpClient = HttpClients.createDefault();
		
		List<NameValuePair> paramList = new ArrayList<NameValuePair>();
		//参数传递
		List<HttpDataEntity> params = entity.getParams();
		
		//header
		List<Header> headers = new ArrayList<Header>();
		
		InputStreamEntity inputStreamEntity = null;
		int paramSize = params.size();
		if(paramSize > 0){
			String key = null;
			Object value = null;
			String type = null;
			//根据类型判断是form字段还是流
			for(HttpDataEntity data : params){
				key = data.getKey();
				value = data.getValue();
				type = data.getType();
				if( StringUtils.isNotBlank(type)){
					if(HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode().equals(type) && StringUtils.isNotBlank(key)){
						paramList.add(new BasicNameValuePair(key, String.valueOf(value)));// 设置请求参数
					}else if(HttpParamType.PARAMTER_TYPE_STREAM.getCode().equals(type)){
						inputStreamEntity = new InputStreamEntity(IOStreamUtil.Str2Inputstr(String.valueOf(value), "UTF-8"));
						continue;
					}else if(HttpParamType.PARAMETER_TYPE_HEADER.getCode().equals(type)){
						headers.add(new BasicHeader(key, String.valueOf(value)));
					}else{
						throw new HttpException("传递参数类型未知, type: "+type);
					}
				}else{
					throw new HttpException("传递的参数键或者参数类型为空, key: "+ key +", value: " + value);
				}
			}
		}
		//生成访问的URI
		URI uri = new URIBuilder(url)
						.addParameters(paramList).build();
		
		HttpPost httppost = new HttpPost(uri);
		RequestConfig requestConfig = RequestConfig.custom().
				setConnectionRequestTimeout((int) entity.getRequesttimeout()).//设置请求连接超时时间
				setSocketTimeout((int) entity.getSotimeout()).//设置传输超时时间
				setConnectTimeout((int)entity.getTimeout()).build();//设置连接超时时间
		
		//Header参数
		for(Header header : headers){
			httppost.addHeader(header);
		}
		
		httppost.setEntity(inputStreamEntity);// 设置请求参数
		httppost.setConfig(requestConfig);//设置参数配置
logger.debug("HTTP请求核心系统......." + entity.toString());		
		resp =  httpClient.execute(httppost);
		HttpResponseCommonEntity resCommonEntity = new HttpResponseCommonEntity();
		
		try {
			
			if (resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity resEntity = resp.getEntity();
				if(resEntity.getContent()!= null){
					res = IOStreamUtil.InputStream2String(resEntity.getContent(), "UTF-8");
				}else if (resEntity != null) {
					res = EntityUtils.toString(resEntity, Consts.UTF_8);
				}
				resCommonEntity.setReturnObj(res);
				resCommonEntity.setReponse(resp);
				// 销毁
				EntityUtils.consume(resEntity);
			}
			
logger.debug("接收核心系统返回报文:" + res);				
		} catch (Exception e) {
			e.printStackTrace();
			throw new HttpException(url);
		}
		return resCommonEntity;
		
	}

	
	/**
	 * 关闭
	 */
	public void close(){
		try {
			if(resp != null)
			resp.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			if(httpClient != null)
			httpClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 针对同步接口使用只传递流的操作
	 * @throws HttpException 
	 */
	public List<HttpDataEntity> parseStringToListParam(String jsonStr) throws HttpException {
		return  parseStringToListParam(null, jsonStr, HttpParamType.PARAMTER_TYPE_STREAM.getCode());
	}
	
	
	/**
	 * 针对同步接口使用只传递流的操作
	 * @throws HttpException 
	 */
	public List<HttpDataEntity> parseStringToListParam(String key, String jsonStr, String paramType) throws HttpException {
		List<HttpDataEntity> list = new ArrayList<HttpDataEntity>();
		if(HttpParamType.PARAMTER_TYPE_STREAM.getCode().equals(paramType)){
			HttpDataEntity data = new HttpDataEntity(key, jsonStr, HttpParamType.PARAMTER_TYPE_STREAM.getCode());
			list.add(data);
		}else if(HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode().equals(paramType)){
			HttpDataEntity data = new HttpDataEntity(key, jsonStr, HttpParamType.PARAMETER_TYPE_PARAMETERS.getCode());
			list.add(data);
		}else{
			throw new HttpException("请求类型未定义");
		}
		return list;
	}

	
}
