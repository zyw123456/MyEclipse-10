package com.sinoway.common.entity;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;

/**
 * 请求返回的
 * @author miles
 *
 */
public class HttpResponseCommonEntity {

	
	public HttpResponseCommonEntity() {
	}
	
	
	
	public HttpResponseCommonEntity(Object returuObj, CloseableHttpResponse reponse) {
		super();
		this.returnObj = returuObj;
		this.reponse = reponse;
	}




	//返回对象
	private Object returnObj;
	// 返回的reponse
	private CloseableHttpResponse reponse;
	
	
	/**
	 * 取得header
	 * @param name
	 * @return
	 */
	public String getHeader(String name){
		Header header = reponse.getFirstHeader(name);
		return header.getValue();
	}
	
	
	/**
	 * 取得多个header
	 * @param name
	 * @return
	 */
	public Header[] getHeaders(String name){
		return reponse.getHeaders(name);
	}
	
	/**
	 * 取得所有header
	 * @return
	 */
	public Header[] getAllHeaders(){
		return reponse.getAllHeaders();
	}
	
	public CloseableHttpResponse getReponse() {
		return reponse;
	}
	
	public void setReponse(CloseableHttpResponse reponse) {
		this.reponse = reponse;
	}



	public Object getReturnObj() {
		return returnObj;
	}



	public void setReturnObj(Object returnObj) {
		this.returnObj = returnObj;
	}
	
	
}
