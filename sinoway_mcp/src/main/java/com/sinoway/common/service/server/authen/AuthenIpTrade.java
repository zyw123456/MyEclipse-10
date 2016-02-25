package com.sinoway.common.service.server.authen;

import com.yzj.wf.com.ibank.common.template.Node;

/**
 * 渠道、ip、交易认证模板配置实体
 * ip_trade
 * @author LiuZhen
 * @version 1.0
 * 2015-11-3
 */
public class AuthenIpTrade extends Node {

	// ip集合
	private String ips = null;
	
	// 交易集合
	private String trades = null;
	
	// 产品集合
	private String prdcods = null;

	/*
	 *  GETTER  AND  SETTER
	 */
	public String getIps() {
		return ips;
	}

	public void setIps(String ips) {
		this.ips = ips;
	}

	public String getTrades() {
		return trades;
	}

	public void setTrades(String trades) {
		this.trades = trades;
	}

	public String getPrdcods() {
		return prdcods;
	}

	public void setPrdcods(String prdcods) {
		this.prdcods = prdcods;
	}
	
	
	
}
