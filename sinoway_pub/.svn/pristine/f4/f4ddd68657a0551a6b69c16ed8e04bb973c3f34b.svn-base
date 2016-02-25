package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefPrdinfo;
import com.sinoway.base.service.BCfgdefPrdinfoService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefPrdinfoCache")
public class BCfgdefPrdinfoCache extends LRUCache{
	@Value("${BCfgdefPrdinfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefPrdinfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefPrdinfoCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefPrdinfoService prdinfoService;
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
		List<BCfgdefPrdinfo> li = prdinfoService.find(map);
		for(BCfgdefPrdinfo prdinfo : li){
			put(prdinfo.getPrdcod(), prdinfo);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefPrdinfo getNewData(Object key) throws CacheException {
		BCfgdefPrdinfo prdinfo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("prdcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefPrdinfo> li = prdinfoService.find(map);
		if(li.size()>0){
			prdinfo = li.get(0);
		}
		return prdinfo;
	}

}
