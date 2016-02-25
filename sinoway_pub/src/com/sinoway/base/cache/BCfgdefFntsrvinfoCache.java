package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefFntsrvinfo;
import com.sinoway.base.service.BCfgdefFntsrvinfoService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefFntsrvinfoCache")
public class BCfgdefFntsrvinfoCache extends LRUCache{
	@Value("${BCfgdefFntsrvinfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFntsrvinfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFntsrvinfoCache.defaultExpire:0}")
	private long defaultExpire;

	@Autowired
	private BCfgdefFntsrvinfoService fntsrvinfoService;
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
		List<BCfgdefFntsrvinfo> li = fntsrvinfoService.find(map);
		for(BCfgdefFntsrvinfo fntsrv : li){
			put(fntsrv.getServcod(), fntsrv);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefFntsrvinfo getNewData(Object key) throws CacheException {
		BCfgdefFntsrvinfo fntsrvinfo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("servcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefFntsrvinfo> li = fntsrvinfoService.find(map);
		if(li.size()>0){
			fntsrvinfo = li.get(0);
		}
		return fntsrvinfo;
	}
	
}
