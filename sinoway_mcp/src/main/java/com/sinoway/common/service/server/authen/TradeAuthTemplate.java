package com.sinoway.common.service.server.authen;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.yzj.wf.com.ibank.common.template.Node;

/**
 * 渠道、ip、交易认证模板配置实体
 * template
 * @author LiuZhen
 * @version 1.0
 * 2015-11-3
 */
public class TradeAuthTemplate extends Node {
	
	// 所有渠道的集合
	private Set<String> chnlSet = null;
	
	// 所有ip的集合
	private Set<String> ipsSet = null;
	
	// 所有trade的集合
	private Set<String> trade = null;
	
	// 所有产品的集合 
	private Set<String> prdcod = null;
	
	// 是否接受所有渠道请求
	boolean isRevAllChnl = false;
	
	// 是否接收所有交易
	boolean isRevAllTrade = false;
	
	// 是否接收所有产品
	boolean isRevAllPrd = false;
	
	// 所有渠道配置集合
	private Map<String,AuthenChnl> chnls = new HashMap<String,AuthenChnl>();
	
	
	/*
	 *    GETTER    AND     SETTER
	 */
	public Set<String> getChnlSet() {
		return chnlSet;
	}

	public void setChnlSet(Set<String> chnlSet) {
		this.chnlSet = chnlSet;
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

	public boolean isRevAllChnl() {
		return isRevAllChnl;
	}

	public void setRevAllChnl(boolean isRevAllChnl) {
		this.isRevAllChnl = isRevAllChnl;
	}

	public boolean isRevAllTrade() {
		return isRevAllTrade;
	}

	public void setRevAllTrade(boolean isRevAllTrade) {
		this.isRevAllTrade = isRevAllTrade;
	}



	public Map<String, AuthenChnl> getChnls() {
		return chnls;
	}

	public void setChnls(Map<String, AuthenChnl> chnls) {
		this.chnls = chnls;
	}

	public Set<String> getPrdcod() {
		return prdcod;
	}

	public void setPrdcod(Set<String> prdcod) {
		this.prdcod = prdcod;
	}

	public boolean isRevAllPrd() {
		return isRevAllPrd;
	}

	public void setRevAllPrd(boolean isRevAllPrd) {
		this.isRevAllPrd = isRevAllPrd;
	}
	
	
	
}
