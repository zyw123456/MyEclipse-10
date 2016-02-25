package com.sinoway.common.cache.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * 用于存放缓存对象
 * @author xiehao
 *
 */

@Component("cacheMapper")
public class CacheMapper implements ICacheMapper{
	private static Map<Object,Long> map = new HashMap<Object,Long>();

	public void putCache(Object cache,long time){
		map.put(cache, time);
	}

	public Map<Object, Long> getCacheMap() {
		return map;
	}
}
