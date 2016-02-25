package com.sinoway.common.service.server.authen;

import com.sinoway.common.exception.TradeAuthException;

/**
 * 认证模板解析接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-2
 */
public interface AuthenTemplateFactory {

	/**
	 * 判断是否接收该ip的请求
	 * @param ip
	 * @return true 是 false 否
	 * @throws TradeAuthException
	 */
	public boolean isIpCanDo(String ip) throws TradeAuthException;
	
	/**
	 * 判断是否接收交易码的请求
	 * @param tradeCode
	 * @return true 是 false 否
	 * @throws TradeAuthException
	 */
	public boolean isTradeCanDo(String tradeCode) throws TradeAuthException;
	
	/**
	 * 判断是否接收该产品的请求
	 * @param prdCode
	 * @return true 是 false 否
	 * @throws TradeAuthException
	 */
	public boolean isPrdCanDo(String prdCode) throws TradeAuthException;
	
	/**
	 * 判断是否接收该渠道的请求
	 * @param chnlCode
	 * @return true 是 false 否
	 * @throws TradeAuthException
	 */
	public boolean isChnlCanDo(String chnlCode) throws TradeAuthException;
	
	/**
	 * 该渠道下是否接收此ip的请求
	 * @param chnlCode
	 * @param ip
	 * @return true 是 false 否 
	 * @throws TradeAuthException
	 */
	public boolean isChnlIpCanDo(String chnlCode,String ip) throws TradeAuthException;
	
	/**
	 * 该渠道下是否接收此交易的请求
	 * @param chnlCode
	 * @param tradeCode
	 * @return true 是 false 否 
	 * @throws TradeAuthException
	 */
	public boolean isChnlTradeCanDo(String chnlCode,String tradeCode) throws TradeAuthException;
	
	/**
	 * 该渠道下是否接收此产品的请求
	 * @param chnlCode
	 * @param prdCode
	 * @return true 是 false 否 
	 * @throws TradeAuthException
	 */
	public boolean isChnlPrdCanDo(String chnlCode,String prdCode) throws TradeAuthException;
	
	/**
	 * 该渠道的ip是否可做此交易
	 * @param chnlCode
	 * @param ip
	 * @param tradeCode
	 * @return true 是 false 否 
	 * @throws TradeAuthException
	 */
	public boolean isChnlIpTradeCanDo(String chnlCode,String ip,String tradeCode) throws TradeAuthException;
	
	/**
	 * 该渠道的ip是否可做此产品
	 * @param chnlCode
	 * @param ip
	 * @param prdCode
	 * @return true 是 false 否 
	 * @throws TradeAuthException
	 */
	public boolean isChnlIpPrdCanDo(String chnlCode,String ip,String prdCode) throws TradeAuthException;
	
}
