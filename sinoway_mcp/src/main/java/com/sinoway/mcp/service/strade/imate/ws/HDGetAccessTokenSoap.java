package com.sinoway.mcp.service.strade.imate.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "HD_GetAccess_TokenSoap", targetNamespace = "http://tempuri.org/")
public interface HDGetAccessTokenSoap {

	/**
	 * ��ȡACCESS_TOKENƾ֤
	 * 
	 * @param key
	 * @param appSecret
	 * @param appID
	 * @return returns java.lang.String
	 */
	@WebMethod(operationName = "GetACCESS_TOKEN", action = "http://tempuri.org/GetACCESS_TOKEN")
	@WebResult(name = "GetACCESS_TOKENResult", targetNamespace = "http://tempuri.org/")
	@RequestWrapper(localName = "GetACCESS_TOKEN", targetNamespace = "http://tempuri.org/", className = "com.sinoway.mcp.service.strade.imate.ws.GetACCESSTOKEN")
	@ResponseWrapper(localName = "GetACCESS_TOKENResponse", targetNamespace = "http://tempuri.org/", className = "com.sinoway.mcp.service.strade.imate.ws.GetACCESSTOKENResponse")
	public String getACCESSTOKEN(
			@WebParam(name = "AppID", targetNamespace = "http://tempuri.org/") String appID,
			@WebParam(name = "AppSecret", targetNamespace = "http://tempuri.org/") String appSecret,
			@WebParam(name = "Key", targetNamespace = "http://tempuri.org/") String key);

}