package com.sinoway.common.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.yzj.wf.com.ibank.common.template.Node;
import com.yzj.wf.com.ibank.common.template.Param;
import com.yzj.wf.com.ibank.common.template.Params;

/**
 * 产品配置实体
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class Product extends Node implements Cloneable {
	private String id = null;
	private String name = null;
	private String chnlCode = null;
	private Params params; // 模板参数
	private Map<String,Message> msgMap = new HashMap<String,Message>();// 报文模板Map

	public Product() {
		super();
	}

	public Product(Node parent) {
		super(parent);
	}
	
	/**
	 * 克隆产品配置
	 */
	public Product clone() throws CloneNotSupportedException {
		Product clonePrd = (Product)super.clone();
		clonePrd.setParams(params.clone());
		
		Map<String,Message> cloneMessages = new HashMap<String,Message>();
		Set<String> keys = msgMap.keySet();
		for(String key : keys){
			Message cloneMessage =  msgMap.get(key).clone();
			cloneMessages.put(cloneMessage.getType(), cloneMessage);
		}
		clonePrd.setMsgMap(cloneMessages);
		
		return clonePrd;
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
	 * GETTER   AND  SETTER
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

	public String getChnlCode() {
		return chnlCode;
	}

	public void setChnlCode(String chnlCode) {
		this.chnlCode = chnlCode;
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
