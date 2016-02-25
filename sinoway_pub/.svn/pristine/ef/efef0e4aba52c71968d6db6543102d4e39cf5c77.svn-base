package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefExpinfo;
import com.sinoway.base.service.BCfgdefExpinfoService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefExpinfoCache")
public class BCfgdefExpinfoCache extends LRUCache{
	@Value("${BCfgdefExpinfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefExpinfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefExpinfoCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgdefExpinfoService expinfoService;
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
		List<BCfgdefExpinfo> li = expinfoService.find(map);
		for(BCfgdefExpinfo expinfo : li){
			put(expinfo.getExpcod(), expinfo);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefExpinfo getNewData(Object key) throws CacheException {
		BCfgdefExpinfo expinfo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("expcod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefExpinfo> li = expinfoService.find(map);
		if(li.size()>0){
			expinfo = li.get(0);
		}
		return expinfo;
	}

}
