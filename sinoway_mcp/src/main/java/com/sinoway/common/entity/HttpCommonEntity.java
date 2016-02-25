package com.sinoway.common.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Http 通用实体类
 * @author miles
 *
 */
public class HttpCommonEntity {

	
	/**
	 * url地址:get请求可以在URL中拼装传入
	 */
	private String url = "http://127.0.0.1:8080/sinoway_plat/McpHttpServlet";

	/**
	 * Http方法
	 */
	private String method = "post";
	
	/**
	 * 请求连接超时时间
	 */
	private long requesttimeout = 1200000;
	/**
	 * 超时时间 设置默认
	 */
	private long timeout = 1200000;
	
	/**
	 * 等待客户连接的超时时间
	 */
	private long sotimeout = 1200000;
	
	
	/**
	 * 参数实体
	 */
	private List<HttpDataEntity> params = new ArrayList<HttpDataEntity>();
	

	/**
	 * 返回结果
	 */
	private Object response;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	
	public long getRequesttimeout() {
		return requesttimeout;
	}

	public void setRequesttimeout(long requesttimeout) {
		this.requesttimeout = requesttimeout;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

	public long getSotimeout() {
		return sotimeout;
	}

	public void setSotimeout(long sotimeout) {
		this.sotimeout = sotimeout;
	}

	public List<HttpDataEntity> getParams() {
		return params;
	}

	public void setParams(List<HttpDataEntity> params) {
		this.params = params;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

	@Override
	public String toString() {
		return "HttpCommonEntity [url=" + url + ", method=" + method + ", requesttimeout=" + requesttimeout
				+ ", timeout=" + timeout + ", sotimeout=" + sotimeout + ", params=" + params + ", response=" + response
				+ "]";
	}
	
	
}
