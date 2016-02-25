package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefOrgblack;
import com.sinoway.base.service.BCfgdefOrgblackService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefOrgblackCache")
public class BCfgdefOrgblackCache extends LRUCache{
	@Value("${BCfgdefOrgblackCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefOrgblackCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefOrgblackCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefOrgblackService orgblackService;
	@Autowired
	private ICacheMapper cacheMapper; 
	
	@PostConstruct  
	public synchronized  void init() {
		initCacheParam();
		if(isOpen){
			loadAll();
			cacheMapper.putCache(this, defaultExpire);
		}
	}

	public void initCacheParam(){
		setCacheSize(cacheSize);
		setDefaultExpire(defaultExpire);
		setCacheMap(getCacheMap());
	}
	
	public synchronized  void loadAll() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("sta", "1");
		List<BCfgdefOrgblack> li = orgblackService.find(map);
		for(BCfgdefOrgblack orgblack : li){
			put(orgblack.getOrgno(), orgblack);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefOrgblack getNewData(Object key) throws CacheException {
		BCfgdefOrgblack orgblack = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgno",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefOrgblack> li = orgblackService.find(map);
		if(li.size()>0){
			orgblack = li.get(0);
		}
		return orgblack;
	}

}
