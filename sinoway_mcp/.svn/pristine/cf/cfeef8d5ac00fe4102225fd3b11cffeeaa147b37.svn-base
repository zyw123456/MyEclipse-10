package com.sinoway.common.service.server.authen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import com.sinoway.common.exception.TradeAuthException;
import com.sinoway.common.util.DocumentUtil;
import com.sinoway.common.util.McpLogger;
import com.sun.org.apache.xpath.internal.XPathAPI;
import com.yzj.wf.com.ibank.common.template.Node;

public class AuthenTemplateFactoryImpl implements  AuthenTemplateFactory{

	// 公共日志类
	private McpLogger logger = McpLogger.getLogger(this.getClass());
	
	// 模板配置节点
	TradeAuthTemplate ttmp = null;
	
	// 加载路径
	private String configPath = null;
	
	/**
	 * 根据配置的路径，解析模板
	 */
	public void init() throws Exception{
		
		logger.info("开始解析渠道、ip、交易认证模板，模板路径为：" + configPath);
		if(configPath == null || "".equals(configPath)){
			logger.error("渠道、ip、交易认证模板错误: 模板路径不能为空");
			throw new Exception("渠道、ip、交易认证模板错误: 模板路径不能为空");
		}
		Document dom = DocumentUtil.filePToDomByNoDecode(configPath);
		
		org.w3c.dom.Node tmpNode = XPathAPI.selectSingleNode(dom, "/configration");
		
		if(tmpNode == null){
			logger.warn("渠道、ip、交易认证模板配置不能解析，找不到configration节点: 模板路径：" + configPath);
		}else{

			
			Element tmpEl = (Element)tmpNode;
			
			// 解析一个tmplate节点
			parseTradeAuthTemp(tmpEl);
		
		}
	}
	
	/**
	 * 解析模板中的template节点
	 * @param el
	 * @return
	 * @throws Exception 
	 */
	private TradeAuthTemplate parseTradeAuthTemp(Element el) throws Exception{
		
		
		if(el != null){
			
			ttmp = new TradeAuthTemplate();
			
			// 获取所有chnl节点
			NodeList chnlNodes = XPathAPI.selectNodeList(el, ".//chnl");
			
			if(chnlNodes != null ){
				
				// 所有chnl节点集合
				Map<String,AuthenChnl> chnls = new HashMap<String,AuthenChnl>();
				
				// 所有渠道号集合
				Set<String> chnlSet = new HashSet<String>();
				
				for(int i = 0; i < chnlNodes.getLength(); i++){
					Element chnlEl = (Element)chnlNodes.item(i);
					
					AuthenChnl auChnl = parseTradeChnlAuthTemp(chnlEl,ttmp);
					
					if(auChnl != null){
						
						String chnlCode = auChnl.getCode();
						if(chnlCode != null && !"".equals(chnlCode)){
							chnls.put(chnlCode,auChnl);
							chnlSet.add(chnlCode);
						}else{
							logger.warn("解析认证模板异常:渠道号不能为空");
						}
					}
				}
				ttmp.setChnls(chnls);
				ttmp.setChnlSet(chnlSet);
			}
			
			// 获取所有ip节点
			NodeList ipNodes = XPathAPI.selectNodeList(el, ".//ip");
			if(ipNodes != null){
				// 所有ip的集合
				Set<String> ipsSet = new HashSet<String>();
				for(int i = 0; i < ipNodes.getLength();i++){
					Element ipEl = (Element)ipNodes.item(i);
					String ips = ipEl.getTextContent();
					String[] ipA = ips.split("\\|");
					for(String ip : ipA){
						ipsSet.add(ip);
					}
				}
				
				ttmp.setIpsSet(ipsSet);
			}
			
			// 获取所有trade节点
			NodeList tradeNodes = XPathAPI.selectNodeList(el, ".//trade");
			if(ipNodes != null){
				// 所有trade的集合
				Set<String> tradeSet = new HashSet<String>();
				for(int i = 0; i < tradeNodes.getLength();i++){
					Element tradeEl = (Element)tradeNodes.item(i);
					String trades = tradeEl.getTextContent();
					String[] tradeA = trades.split("\\|");
					for(String trade : tradeA){
						if("*".equals(trade))
							ttmp.setRevAllTrade(true);
						tradeSet.add(trade);
					}
				}
				
				ttmp.setTrade(tradeSet);
			}
			
			// 获取所有prd节点
			NodeList prdNodes = XPathAPI.selectNodeList(el, ".//prd");
			if(ipNodes != null){
				// 所有prd的集合
				Set<String> prdSet = new HashSet<String>();
				for(int i = 0; i < prdNodes.getLength();i++){
					Element prdEl = (Element)prdNodes.item(i);
					String prds = prdEl.getTextContent();
					String[] prdCodeA = prds.split("\\|");
					for(String prdcode : prdCodeA){
						if("*".equals(prdcode))
							ttmp.setRevAllPrd(true);
						
						prdSet.add(prdcode);
					}
				}
				
				ttmp.setPrdcod(prdSet);
			}
			
		}
		return ttmp;
	}
	
	/**
	 * 解析模板中的chnl节点
	 * @param el
	 * @return
	 */
	private AuthenChnl parseTradeChnlAuthTemp(Element el,Node parent) throws Exception{
		
		AuthenChnl actmp = null;
		if(el != null){
			actmp = new AuthenChnl();
			
			actmp.setParent(parent);
			
			// 渠道编码
			String code = el.getAttribute("code");

			actmp.setCode(code);
			
			// 获取所有 ip_trade节点
			NodeList ipTradeNodes = XPathAPI.selectNodeList(el, ".//ip_trade");
			
			if(ipTradeNodes != null){
				
				List<AuthenIpTrade> ipTrades = new ArrayList<AuthenIpTrade>();
				
				for(int i = 0; i < ipTradeNodes.getLength(); i++){
					
					Element ipTradeNode = (Element)ipTradeNodes.item(i);
					
					AuthenIpTrade ipTrade = parseIpTradeAuthTemp(ipTradeNode,actmp);
					
					if(ipTrade != null){
						ipTrades.add(ipTrade);
					}
				}
				
				actmp.setIpTrades(ipTrades);
				
			}
			
			// 获取所有ip节点
			NodeList ipNodes = XPathAPI.selectNodeList(el, ".//ip");
			if(ipNodes != null){
				// 所有ip的集合
				Set<String> ipsSet = new HashSet<String>();
				for(int i = 0; i < ipNodes.getLength();i++){
					Element ipEl = (Element)ipNodes.item(i);
					String ips = ipEl.getTextContent();
					String[] ipA = ips.split("\\|");
					for(String ip : ipA){
						ipsSet.add(ip);
					}
				}
				
				actmp.setIpsSet(ipsSet);
			}
			
			// 获取所有trade节点
			NodeList tradeNodes = XPathAPI.selectNodeList(el, ".//trade");
			if(ipNodes != null){
				// 所有trade的集合
				Set<String> tradeSet = new HashSet<String>();
				for(int i = 0; i < tradeNodes.getLength();i++){
					Element tradeEl = (Element)tradeNodes.item(i);
					String trades = tradeEl.getTextContent();
					String[] tradeA = trades.split("\\|");
					for(String trade : tradeA){
						if("*".equals(trade))
							ttmp.setRevAllTrade(true);
						tradeSet.add(trade);
					}
				}
				
				actmp.setTrade(tradeSet);
			}
			
			// 获取所有prd节点
			NodeList prdNodes = XPathAPI.selectNodeList(el, ".//prd");
			if(ipNodes != null){
				// 所有prd的集合
				Set<String> prdSet = new HashSet<String>();
				for(int i = 0; i < prdNodes.getLength();i++){
					Element prdEl = (Element)prdNodes.item(i);
					String prds = prdEl.getTextContent();
					String[] prdCodeA = prds.split("\\|");
					for(String prdcode : prdCodeA){
						if("*".equals(prdcode))
							ttmp.setRevAllPrd(true);
						
						prdSet.add(prdcode);
					}
				}
				
				actmp.setPrdcod(prdSet);
			}
			
		}

		return actmp;
	}
	
	/**
	 * 解析ip_trade节点 
	 * @param el
	 * @return
	 */
	private AuthenIpTrade parseIpTradeAuthTemp(Element el,Node parent) throws Exception{
		
		AuthenIpTrade ipTrade = null;
		
		if(el != null){
			
			ipTrade = new AuthenIpTrade();
			
			ipTrade.setParent(parent);
			
			NodeList ipNodes = XPathAPI.selectNodeList(el, ".//ip");
			
			String ips = "";
			
			if(ipNodes != null){
				for(int i = 0; i < ipNodes.getLength(); i++){
					Element ipNode = (Element)ipNodes.item(i);
					
					ips = "|" + ips + ipNode.getTextContent();
				}
				ipTrade.setIps(ips);
			}
			
			NodeList tradeNodes = XPathAPI.selectNodeList(el, ".//trade");
			
			String trades = "";
			
			if(tradeNodes != null){
				for(int i = 0; i < tradeNodes.getLength(); i++){
					Element tradeNode = (Element)tradeNodes.item(i);
					
					trades = "|" + trades + tradeNode.getTextContent();
				}
				ipTrade.setTrades(trades);
			}
			
			NodeList prdsNodes = XPathAPI.selectNodeList(el, ".//prd");
			
			String prds = "";
			
			if(prdsNodes != null){
				for(int i = 0; i < prdsNodes.getLength(); i++){
					Element prdsNode = (Element)prdsNodes.item(i);
					
					prds = "|" + prds + prdsNode.getTextContent();
				}
				ipTrade.setPrdcods(prds);
			}
		
		}
		
		return ipTrade;
	}
	
	@Override
	public boolean isChnlCanDo(String chnlCode) throws TradeAuthException {
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道认证错误：渠道编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道认证错误：配置模板未加载");
		
		Set<String> chnls = ttmp.getChnlSet();
		if(chnls != null){
			return chnls.contains(chnlCode);
		}
		
		return false;
	}

	@Override
	public boolean isChnlIpCanDo(String chnlCode, String ip)
			throws TradeAuthException {
		if(ip == null || ip.equals(""))
			throw new TradeAuthException("渠道IP认证错误：IP不能为空");
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道IP认证错误：渠道编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道IP认证错误：配置模板未加载");
		
		Map<String,AuthenChnl> chnls = ttmp.getChnls();
		if(chnls != null){
			AuthenChnl chnl = chnls.get(chnlCode);
			
			if(chnl != null){
				Set<String> ips = chnl.getIpsSet();
				
				if(ips != null)
					return ips.contains(ip);
			}
			
		}
		
		return false;
	}

	@Override
	public boolean isChnlIpPrdCanDo(String chnlCode, String ip, String prdCode)
			throws TradeAuthException {
		
		if(ip == null || ip.equals(""))
			throw new TradeAuthException("渠道IP产品认证错误：IP不能为空");
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道IP产品认证错误：渠道编码不能为空");
		if(prdCode == null || prdCode.equals(""))
			throw new TradeAuthException("渠道IP产品认证错误：产品编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道IP产品认证错误：配置模板未加载");
		
		Map<String,AuthenChnl> chnls = ttmp.getChnls();
		if(chnls != null){
			AuthenChnl chnl = chnls.get(chnlCode);
			
			if(chnl != null){
				List<AuthenIpTrade> iptrades = chnl.getIpTrades();
				if(iptrades != null && iptrades.size() > 0){
					for(AuthenIpTrade iptrade : iptrades){
						String ips = iptrade.getIps();
						String prds = iptrade.getPrdcods();
						if(ips == null || "".equals(ips))
							continue;
						if(prds == null || "".equals(prds))
							continue;
						
						if(ips.contains(ip)){
							if(prds.contains("*") || prds.contains(prdCode))
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isChnlIpTradeCanDo(String chnlCode, String ip,
			String tradeCode) throws TradeAuthException {
		
		if(ip == null || ip.equals(""))
			throw new TradeAuthException("渠道IP交易认证错误：IP不能为空");
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道IP交易认证错误：渠道编码不能为空");
		if(tradeCode == null || tradeCode.equals(""))
			throw new TradeAuthException("渠道IP交易认证错误：交易编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道IP交易认证错误：配置模板未加载");
		
		Map<String,AuthenChnl> chnls = ttmp.getChnls();
		if(chnls != null){
			AuthenChnl chnl = chnls.get(chnlCode);
			
			if(chnl != null){
				List<AuthenIpTrade> iptrades = chnl.getIpTrades();
				if(iptrades != null && iptrades.size() > 0){
					for(AuthenIpTrade iptrade : iptrades){
						String ips = iptrade.getIps();
						String  trades = iptrade.getTrades();
						if(ips == null || "".equals(ips))
							continue;
						if(trades == null || "".equals(trades))
							continue;
						
						if(ips.contains(ip)){
							if(trades.contains("*") || trades.contains(tradeCode))
								return true;
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public boolean isChnlPrdCanDo(String chnlCode, String prdCode)
			throws TradeAuthException {
		
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道产品认证错误：渠道编码不能为空");
		if(prdCode == null || prdCode.equals(""))
			throw new TradeAuthException("渠道产品认证错误：产品编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道产品认证错误：配置模板未加载");
		
		Map<String,AuthenChnl> chnls = ttmp.getChnls();
		if(chnls != null){
			AuthenChnl chnl = chnls.get(chnlCode);
			
			if(chnl != null){
				Set<String> prds = chnl.getPrdcod();
				if(prds != null)
					return (prds.contains(prdCode) || prds.contains("*"));
			}
		}
		return false;
	}

	@Override
	public boolean isChnlTradeCanDo(String chnlCode, String tradeCode)
			throws TradeAuthException {
		if(chnlCode == null || chnlCode.equals(""))
			throw new TradeAuthException("渠道交易认证错误：渠道编码不能为空");
		if(tradeCode == null || tradeCode.equals(""))
			throw new TradeAuthException("渠道交易认证错误：交易编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("渠道交易认证错误：配置模板未加载");
		
		Map<String,AuthenChnl> chnls = ttmp.getChnls();
		if(chnls != null){
			AuthenChnl chnl = chnls.get(chnlCode);
			
			if(chnl != null){
				Set<String> trades = chnl.getTrade();
				if(trades != null)
					return (trades.contains(tradeCode) || trades.contains("*"));
			}
		}
		return false;
	}

	@Override
	public boolean isIpCanDo(String ip) throws TradeAuthException {
		if(ip == null || ip.equals(""))
			throw new TradeAuthException("IP认证错误：IP不能为空");
		if(ttmp == null)
			throw new TradeAuthException("IP认证错误：配置模板未加载");
		
		Set<String> ips = ttmp.getIpsSet();
		if(ips != null){
			return ips.contains(ip);
		}
		
		return false;
	}

	@Override
	public boolean isPrdCanDo(String prdCode) throws TradeAuthException {
		if(prdCode == null || prdCode.equals(""))
			throw new TradeAuthException("产品认证错误：产品编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("产品认证错误：配置模板未加载");
		
		Set<String> prds = ttmp.getPrdcod();
		if(prds != null){
			return (prds.contains(prdCode) || prds.contains("*"));
		}
		
		return false;
	}

	@Override
	public boolean isTradeCanDo(String tradeCode) throws TradeAuthException {
		if(tradeCode == null || tradeCode.equals(""))
			throw new TradeAuthException("交易认证错误：交易编码不能为空");
		if(ttmp == null)
			throw new TradeAuthException("交易认证错误：配置模板未加载");
		
		Set<String> trades = ttmp.getTrade();
		if(trades != null){
			return (trades.contains(tradeCode) || trades.contains("*"));
		}
		
		return false;
	}
	
	/*
	 *  GETTER    AND   SETTER
	 */
	public String getConfigPath() {
		return configPath;
	}
	public void setConfigPath(String configPath) {
		this.configPath = configPath;
	}

}
