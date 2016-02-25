package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefCompusr;
import com.sinoway.base.service.BCfgdefCompusrService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefCompusrCache")
public class BCfgdefCompusrCache extends LRUCache{

	@Value("${BCfgdefCompusrCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefCompusrCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefCompusrCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgdefCompusrService compusrService;
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
		List<BCfgdefCompusr> li = compusrService.find(map);
		for(BCfgdefCompusr compusr : li){
			put(compusr.getUsrid(), compusr);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefCompusr getNewData(Object key) throws CacheException {
		BCfgdefCompusr compusr = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usrid",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefCompusr> li = compusrService.find(map);
		if(li.size()>0){
			compusr = li.get(0);
		}
		return compusr;
	}

}
