package com.sinoway.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.sinoway.common.entity.Message;
import com.sinoway.common.entity.Product;
import com.sinoway.common.entity.Trade;
import com.sinoway.common.entity.TradeTemplate;
import com.sinoway.common.exception.DomParseException;
import com.sun.org.apache.xpath.internal.XPathAPI;
import com.yzj.wf.com.ibank.common.template.Param;
import com.yzj.wf.com.ibank.common.template.Params;

/**
 * 交易模板配置解析工具
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class TemplateParseUtil {
	
	private String configPath = null;
	
	public TemplateParseUtil(String configPath){
		this.configPath = configPath;
	}

	/**
	 * 解析交易配置模板
	 * @return
	 */
	public TradeTemplate getTradeTemplate() throws Exception{
		
		TradeTemplate tempLate = new TradeTemplate(null);
		
		Document dom = DocumentUtil.filePToDomByDefDecode(configPath);
		
		Element rootElement = (Element)XPathAPI.selectSingleNode(dom, "/TRADETEMPLATE");
		
		parseTempLate(tempLate,rootElement);
		
		Params params = new Params(tempLate);
		
		Element paramEl = (Element)XPathAPI.selectSingleNode(rootElement, "./PARAMS");

		parseParams(params,paramEl);
		
		tempLate.setParams(params);
		
		NodeList tradeList = XPathAPI.selectNodeList(rootElement,".//TRADE");
		List<Trade> trades = new ArrayList<Trade>();
		
		for(int i = 0 ; i < tradeList.getLength(); i++){
			Element tradeEl = (Element)tradeList.item(i);
			Trade trade = new Trade(tempLate);
			trade.setChnl(tradeEl.getAttribute("CHANL"));
			trade.setDesc(tradeEl.getAttribute("DESC"));
			trade.setId(tradeEl.getAttribute("ID"));
			trade.setName(tradeEl.getAttribute("NAME"));
			trade.setMsgType(tradeEl.getAttribute("MSGTYPE"));
			parseTrade(trade,tradeEl);
			trades.add(trade);
		}
		
		tempLate.setTrades(trades);
		
		NodeList prdList = XPathAPI.selectNodeList(rootElement,".//PRD");
		
		List<Product> products = new ArrayList<Product>();
		for(int i = 0 ; i < prdList.getLength(); i++){
			Element prdEl = (Element)prdList.item(i);
			Product prd = new Product(tempLate);
			prd.setChnlCode(prdEl.getAttribute("CHANL"));
			prd.setDesc(prdEl.getAttribute("DESC"));
			prd.setId(prdEl.getAttribute("ID"));
			prd.setName(prdEl.getAttribute("NAME"));
			parsePrd(prd,prdEl);
			products.add(prd);
		}
		
		tempLate.setProducts(products);
		
		return tempLate;
	}
	
	/**
	 * 解析参数
	 * @param tradeTemplate
	 * @param el
	 */
	private void parseTempLate(TradeTemplate tradeTemplate,Element el){
		tradeTemplate.setDesc(el.getAttribute("DESC"));
	}
	
	/**
	 * 解析参数
	 * @param params
	 * @param el
	 */
	private void parseParams(Params params,Element el) throws Exception{
		NodeList nl = XPathAPI.selectNodeList(el, ".//PARAM");
		List<Param> ps = params.getParams();
		for (int i = 0; i < nl.getLength(); i++) {
			Element paramEl = (Element)nl.item(i);
			Param param = new Param(params);
			param.setName(paramEl.getAttribute("NAME"));
			param.setValue(paramEl.getAttribute("VALUE"));
			param.setDesc(paramEl.getAttribute("DESC"));
			ps.add(param);
		}
	}
	
	/**
	 * 解析交易配置
	 * @param trade
	 * @param el
	 * @throws Exception
	 */
	private void parseTrade(Trade trade,Element el) throws Exception{
		
		Element paramsEl = (Element)XPathAPI.selectSingleNode(el, "./PARAMS");
		Params params = new Params(trade);
		parseParams(params,paramsEl);
		trade.setParams(params);
		
		NodeList msgEls = XPathAPI.selectNodeList(el, ".//MESSAGE");
		Map<String,Message> msgMap = new HashMap<String,Message>();
		
		for(int i = 0 ; i < msgEls.getLength(); i++){
			Element msgEl = (Element)msgEls.item(i);
			Message msg = new Message(trade);
			msg.setDesc(msgEl.getAttribute("DESC"));
			String type= msgEl.getAttribute("TYPE");
			msg.setType(type);
			Element ele = (Element)msgEl.getFirstChild();
			msg.setMsgEl(ele);
			msgMap.put(type, msg);
		}
		trade.setMsgMap(msgMap);
		
	}
	/**
	 * 解析产品配置节点
	 * @param product
	 * @param el
	 * @throws Exception
	 */
	private void parsePrd(Product product,Element el) throws Exception{
		Element paramsEl = (Element)XPathAPI.selectSingleNode(el, "./PARAMS");
		Params params = new Params(product);
		
		parseParams(params,paramsEl);
		
		product.setParams(params);
		
		NodeList msgEls = XPathAPI.selectNodeList(el, ".//MESSAGE");
		Map<String,Message> msgMap = new HashMap<String,Message>();
		
		for(int i = 0 ; i < msgEls.getLength(); i++){
			Element msgEl = (Element)msgEls.item(i);
			Message msg = new Message(product);
			msg.setDesc(msgEl.getAttribute("DESC"));
			String type= msgEl.getAttribute("TYPE");
			msg.setType(type);
			Element ele = (Element)msgEl.getFirstChild();
			msg.setMsgEl(ele);
			msgMap.put(type, msg);
		}
		product.setMsgMap(msgMap);
	}
	
	public String getConfigPath() {
		return configPath;
	}

	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}// 模板配置路径

}
