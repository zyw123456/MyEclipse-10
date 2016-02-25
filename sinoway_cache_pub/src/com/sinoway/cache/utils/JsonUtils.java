package com.sinoway.cache.utils;

import com.alibaba.fastjson.JSON;

public class JsonUtils {

	/**
	 * @param o
	 * @return
	 * 对象转化成字符串
	 */
	public static String objectToString(Object o){
		
		try {
	
			return JSON.toJSONString(o);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("对象转成string时发生错误");
			return null;
		}
		
	}
	/**
	 * @param content
	 * @param type
	 * @return
	 * 字符串转化成对象
	 */
	public static Object stringToObject(String content){
		
		try {
			return JSON.parseObject(content).toJSONString();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(content+"-->string转成对象时发生错误");
		}
		return null;
		
	}
	
	
	
}
