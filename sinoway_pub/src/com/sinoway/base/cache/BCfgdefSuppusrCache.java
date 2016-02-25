package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefSuppusr;
import com.sinoway.base.service.BCfgdefSuppusrService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefSuppusrCache")
public class BCfgdefSuppusrCache extends LRUCache{
	@Value("${BCfgdefSuppusrCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefSuppusrCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefSuppusrCache.defaultExpire:0}")
	private long defaultExpire;

	@Autowired
	private BCfgdefSuppusrService suppusrService;
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
		List<BCfgdefSuppusr> li = suppusrService.find(map);
		for(BCfgdefSuppusr suppusr : li){
			put(suppusr.getUsrid(), suppusr);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefSuppusr getNewData(Object key) throws CacheException {
		BCfgdefSuppusr suppusr = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usrid",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefSuppusr> li = suppusrService.find(map);
		if(li.size()>0){
			suppusr = li.get(0);
		}
		return suppusr;
	}

}
