package com.sinoway.common.cache.impl;

import java.util.Map;

public interface ICacheMapper {
	public void putCache(Object cache,long time);
	public Map<Object,Long> getCacheMap();
}
