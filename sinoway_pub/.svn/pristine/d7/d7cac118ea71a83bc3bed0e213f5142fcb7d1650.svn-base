package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefFntcustsrv;
import com.sinoway.base.service.BCfgdefFntcustsrvService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefFntcustsrvCache")
public class BCfgdefFntcustsrvCache extends LRUCache{
	@Value("${BCfgdefFntcustsrvCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFntcustsrvCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFntcustsrvCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgdefFntcustsrvService fntcustsrvService;
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
		List<BCfgdefFntcustsrv> li = fntcustsrvService.find(map);
		for(BCfgdefFntcustsrv fntcustsrv : li){
			put(fntcustsrv.getServcod(), fntcustsrv);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefFntcustsrv getNewData(Object key) throws CacheException {
		BCfgdefFntcustsrv fntcustsrv = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("servcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefFntcustsrv> li = fntcustsrvService.find(map);
		if(li.size()>0){
			fntcustsrv = li.get(0);
		}
		return fntcustsrv;
	}

}
