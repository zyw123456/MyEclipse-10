package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefChnltrd;
import com.sinoway.base.service.BCfgdefChnltrdService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefBcfgdefchnltrdCache")
public class BCfgdefChnltrdCache extends LRUCache{
	@Value("${BCfgdefChnltrdCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefChnltrdCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefChnltrdCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefChnltrdService ChnltrdService;
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
		List<BCfgdefChnltrd> li = ChnltrdService.find(map);
		for(BCfgdefChnltrd Chnltrd : li){
			put(Chnltrd.getId(), Chnltrd);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefChnltrd getNewData(Object key) throws CacheException {
		BCfgdefChnltrd Chnltrd = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefChnltrd> li = ChnltrdService.find(map);
		if(li.size()>0){
			Chnltrd = li.get(0);
		}
		return Chnltrd;
	}

}
