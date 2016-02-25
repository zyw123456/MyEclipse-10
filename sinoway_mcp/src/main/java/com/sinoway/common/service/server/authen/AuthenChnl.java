package com.sinoway.common.service.server.authen;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yzj.wf.com.ibank.common.template.Node;

/**
 * 渠道、ip、交易认证模板配置实体
 * chnl
 * @author LiuZhen
 * @version 1.0
 * 2015-11-3
 */
public class AuthenChnl extends  Node{

	// 渠道编码 *表示全部
	private String code = null;
	
	// 所有ip的集合
	private Set<String> ipsSet = null;
	
	// 所有trade的集合
	private Set<String> trade = null;
	
	// 所有产品的集合
	private Set<String> prdcod = null;
	
	// 该渠道配置所有ip_trade集合
	private List<AuthenIpTrade> ipTrades = new ArrayList<AuthenIpTrade>();
	
	/*
	 * GETTER   AND   SETTER
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<AuthenIpTrade> getIpTrades() {
		return ipTrades;
	}

	public void setIpTrades(List<AuthenIpTrade> ipTrades) {
		this.ipTrades = ipTrades;
	}

	public Set<String> getIpsSet() {
		return ipsSet;
	}

	public void setIpsSet(Set<String> ipsSet) {
		this.ipsSet = ipsSet;
	}

	public Set<String> getTrade() {
		return trade;
	}

	public void setTrade(Set<String> trade) {
		this.trade = trade;
	}

	public Set<String> getPrdcod() {
		return prdcod;
	}

	public void setPrdcod(Set<String> prdcod) {
		this.prdcod = prdcod;
	}
	
	
	
	
}
