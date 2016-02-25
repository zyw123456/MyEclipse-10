package com.sinoway.common.entity;

import org.w3c.dom.Element;
import com.yzj.wf.com.ibank.common.template.Node;

/**
 * 具体报文配置节点
 * @author Liuzhen
 * 
 *
 */
public class Message extends Node implements Cloneable {
	private String type = null; // 报文类型
	private Element msgEl = null; // 配置节点
	
	
	public Message() {
		super();
	}

	public Message(Node parent) {
		super(parent);
	}
	
	/**
	 * 克隆message
	 */
	public Message clone() throws CloneNotSupportedException {
		Message cloneMsg = (Message)super.clone();
		cloneMsg.setMsgEl((Element)msgEl.cloneNode(true));
		return cloneMsg;
	}
	
	/*
	 *     GETTER    AND  SETTER
	 */
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Element getMsgEl() {
		return msgEl;
	}
	public void setMsgEl(Element msgEl) {
		this.msgEl = msgEl;
	}
	
	
}
