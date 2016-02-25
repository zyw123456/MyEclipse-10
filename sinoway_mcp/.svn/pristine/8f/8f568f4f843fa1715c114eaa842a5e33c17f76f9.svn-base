package com.sinoway.common.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.yzj.wf.com.ibank.common.template.Node;
import com.yzj.wf.com.ibank.common.template.Param;
import com.yzj.wf.com.ibank.common.template.Params;

/**
 * 交易报文配置  交易配置节点
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class Trade extends Node implements Cloneable  {

	private String id = null;// 交易编码
	private String name = null;// 名称
	private String chnl = null; // 渠道编码
	private String msgType = null;// 报文类别
	private Params params; // 模板参数
	private Map<String,Message> msgMap = new HashMap<String,Message>();// 报文模板Map
	
	
	public Trade() {
		super();
	}

	public Trade(Node parent) {
		super(parent);
	}
	
	/**
	 * 克隆交易配置
	 */
	public Trade clone() throws CloneNotSupportedException {
		Trade cloneTrade = (Trade)super.clone();
		
		cloneTrade.setParams(params.clone());
		
		Map<String,Message> cloneMessages = new HashMap<String,Message>();
		Set<String> keys = msgMap.keySet();
		for(String key : keys){
			Message cloneMessage =  msgMap.get(key).clone();
			cloneMessages.put(cloneMessage.getType(), cloneMessage);
		}
		cloneTrade.setMsgMap(cloneMessages);
		
		return cloneTrade;
	}
	
	/**
	 * 根据参数名称返回参数
	 * 
	 * @param name
	 *            参数名称
	 * @return 查找指定参数名称的参数，找不到则返回null<br>
	 *         先查找Trade，找不到再查找父节点TradeTemplate
	 */
	public Param getParamByName(String name) {
		try {
			Param param = params.getParamByName(name);
			if (param == null) {
				return ((TradeTemplate) parent).getParamByName(name);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * 根据参数名称返回参数值
	 * 
	 * @param name
	 *            参数名称
	 * @return 查找指定参数名称的参数值，找不到则返回null<br>
	 *         先查找Trade，找不到再查找父节点TradeTemplate
	 */
	public String getParamValueByName(String name) {
		try {
			Param param = params.getParamByName(name);
			if (param == null) {
				return ((TradeTemplate) parent).getParamValueByName(name);
			} else {
				return param.getValue();
			}
		} catch (Exception e) {
			return "";
		}
	}

	/*
	 * 		GETTER    AND   SETTER
	 */
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChnl() {
		return chnl;
	}

	public void setChnl(String chnl) {
		this.chnl = chnl;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public Map<String, Message> getMsgMap() {
		return msgMap;
	}

	public void setMsgMap(Map<String, Message> msgMap) {
		this.msgMap = msgMap;
	}
	
	
}
