package com.sinoway.common.service.template;

import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.entity.TradeTemplate;

/**
 * 通用报文模板解析接口
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public interface GeneralTemplateService {

	/**
	 * 通过渠道号 交易码获取交易模板
	 * @param chnlCode
	 * @param tradeCode
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Trade getTradeByTemplate(String chnlCode, String tradeCode) throws CloneNotSupportedException;
	
	/**
	 * 通过渠道号 产品码获取交易模板
	 * @param chnlCode
	 * @param prdCode
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Product getPrdByTemplate(String chnlCode,String prdCode) throws CloneNotSupportedException;  
	
	/**
	 * 通过获取交易模板
	 * @param chnlCode
	 * @param prdCode
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public TradeTemplate getTradeTemplate() throws CloneNotSupportedException;
	
}
