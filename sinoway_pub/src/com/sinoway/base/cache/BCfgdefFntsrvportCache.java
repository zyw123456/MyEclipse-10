package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefFntsrvport;
import com.sinoway.base.service.BCfgdefFntsrvportService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefFntsrvportCache")
public class BCfgdefFntsrvportCache extends LRUCache{
	@Value("${BCfgdefFntsrvportCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFntsrvportCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFntsrvportCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefFntsrvportService fntsrvportService;
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
		List<BCfgdefFntsrvport> li = fntsrvportService.find(map);
		for(BCfgdefFntsrvport fntsrvport : li){
			put(fntsrvport.getPortcod(),fntsrvport);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefFntsrvport getNewData(Object key) throws CacheException {
		BCfgdefFntsrvport fntsrvport = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("portcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefFntsrvport> li = fntsrvportService.find(map);
		if(li.size()>0){
			fntsrvport = li.get(0);
		}
		return fntsrvport;
	}
	
}
