package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefInele;
import com.sinoway.base.service.BCfgdefIneleService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefIneleCache")
public class BCfgdefIneleCache extends LRUCache{
	@Value("${BCfgdefIneleCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefIneleCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefIneleCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefIneleService ineleService;
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
		List<BCfgdefInele> li = ineleService.find(map);
		for(BCfgdefInele inele : li){
			put(inele.getElecod(), inele);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefInele getNewData(Object key) throws CacheException {
		BCfgdefInele inele = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("elecod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefInele> li = ineleService.find(map);
		if(li.size()>0){
			inele = li.get(0);
		}
		return inele;
	}
	
}
