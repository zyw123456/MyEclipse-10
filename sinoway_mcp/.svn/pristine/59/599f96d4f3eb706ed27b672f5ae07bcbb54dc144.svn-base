package com.sinoway.common.service.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.entity.TradeTemplate;
import com.sinoway.common.util.McpLogger;
import com.sinoway.common.util.TemplateParseUtil;

/**
 * 通用报文模板解析工厂
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class GeneralTemplateServiceImpl implements GeneralTemplateService {

	private McpLogger logger = McpLogger.getLogger(this.getClass());
	public String configPath = null;// 模板路径
	
	private TradeTemplate tradeTemplate = null;//配置模板
	private Map<String,Trade> tradeMap = null;// 交易配置模板
	private Map<String,Trade> chnlTradeMap = null;// 渠道交易配置模板
	private Map<String,Product> prdMap = null;// 产品配置模板
	private Map<String,Product> chnlPrdMap = null;// 渠道渠道产品配置模板

	
	/**
	 * 初始化配置模板
	 */
	public void init() throws Exception{
		logger.info("开始解析交易配置信息...");
		if(configPath == null || "".equals(configPath))
			throw new Exception("加载交易配置信息配置出错：配置路径为空");
		
		TemplateParseUtil parser = new TemplateParseUtil(configPath);
		
		tradeTemplate = parser.getTradeTemplate();
		
		if(tradeTemplate != null){
			tradeMap = new HashMap<String,Trade>();
			chnlTradeMap = new HashMap<String,Trade>();
			prdMap = new HashMap<String,Product>();
			chnlPrdMap = new HashMap<String,Product>();
			
			List<Trade> tradeList = tradeTemplate.getTrades();
			List<Product> prdList = tradeTemplate.getProducts();
			
			for(Trade trade : tradeList){
				String chnlCode = trade.getChnl();
				String tradeCode = trade.getId();
				if(chnlCode != null && (!"".equals(chnlCode))){
					chnlTradeMap.put(chnlCode + "_" + tradeCode, trade);
				}else{
					tradeMap.put(tradeCode, trade);
				}
					
			}
			
			for(Product prd : prdList){
				String chnlCode = prd.getChnlCode();
				String prdCode = prd.getId();
				if(chnlCode != null || (!"".equals(chnlCode))){
					String key = chnlCode;
					if(prdCode != null && !"".equals(prdCode)){
						key = chnlCode + "_" + prdCode; 
					}
					chnlPrdMap.put(key , prd);
				}else{
					prdMap.put(prdCode, prd);
				}
					
			}
			
		}
		
	}
	@Override
	public Product getPrdByTemplate(String chnlCode, String prdCode)
			throws CloneNotSupportedException {
		Product prd = null;
		if(chnlPrdMap.containsKey(chnlCode + "_" + prdCode))
			return chnlPrdMap.get(chnlCode + "_" + prdCode);
		
		if(prdMap.containsKey(prdCode))
			return prdMap.get(prdCode);
		
		if(chnlPrdMap.containsKey(chnlCode))
			return chnlPrdMap.get(chnlCode);
		
		return null;
	}

	@Override
	public Trade getTradeByTemplate(String chnlCode, String tradeCode)
			throws CloneNotSupportedException {
		Trade trade = chnlTradeMap.get(chnlCode + "_" + tradeCode);
		if(trade == null)
			trade = tradeMap.get(tradeCode);
		return trade;
	}

	@Override
	public TradeTemplate getTradeTemplate() throws CloneNotSupportedException {
		return this.tradeTemplate;
	}
	
	/*
	 * GETTER   AND  SETTER
	 */
	public String getConfigPath() {
		return configPath;
	}
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

}
