package com.sinoway.common.entity;

import java.util.ArrayList;
import java.util.List;
import com.yzj.wf.com.ibank.common.template.Node;
import com.yzj.wf.com.ibank.common.template.Param;
import com.yzj.wf.com.ibank.common.template.Params;

/**
 * 通用交易报文配置模板实体 
 * @author Liuzhen
 * @version 1.0
 * 2015-11-19
 */
public class TradeTemplate extends Node {
	
	private Params params; // 模板参数
	private List<Trade> trades= new ArrayList<Trade>(); // 交易配置集合
	private List<Product> products = new ArrayList<Product>(); // 产品配置集合
	
	
	
	public TradeTemplate() {
		super();
	}
	
	public TradeTemplate(Node parent) {
		super(parent);
	}
	
	/**
	 * 复制模板节点
	 * @throws CloneNotSupportedException
	 */
	public TradeTemplate clone() throws CloneNotSupportedException {
		TradeTemplate cloneObj = (TradeTemplate) super.clone();

		cloneObj.setParams(params.clone());
		
		List<Trade> cloneTrades = new ArrayList<Trade>();
		for (Trade trade : trades) {
			cloneTrades.add(trade.clone());
		}
		cloneObj.setTrades(cloneTrades);
		
		List<Product> clonePrds = new ArrayList<Product>();
		for (Product prd : products) {
			clonePrds.add(prd.clone());
		}
		cloneObj.setProducts(clonePrds);
		
		return cloneObj;
	}
	
	/**
	 * 根据参数名称返回参数
	 * 
	 * @param name 参数名称
	 * @return 查找指定值的Param，找不到则返回null
	 */
	public Param getParamByName(String name){
		return params.getParamByName(name);
	}
	
	/**
	 * 根据参数名称返回参数值
	 * 
	 * @param name 参数名称
	 * @return  查找指定参数名称的参数值，找不到则返回空字符串
	 */
	public String getParamValueByName(String name){
		return params.getParamValueByName(name);
	}
	
	/*
	 *       GETTER   AND  SETTER
	 */
	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
