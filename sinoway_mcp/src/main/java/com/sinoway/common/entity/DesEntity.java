package com.sinoway.common.entity;

/**
 * 对称加密实体
 * @author Liuzhen
 * @version 1.0
 * 2015-12-3
 */
public class DesEntity {

	private String key = null;// 密钥
	private String vector = null;// 向量
	
	/*
	 *   GETTER   AND  SETTER
	 */
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
}
