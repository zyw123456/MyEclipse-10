package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefPrdtrn;
import com.sinoway.base.service.BCfgdefPrdtrnService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefPrdtrnCache")
public class BCfgdefPrdtrnCache extends LRUCache{
	@Value("${BCfgdefPrdtrnCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefPrdtrnCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefPrdtrnCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefPrdtrnService prdtrnService;
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
		List<BCfgdefPrdtrn> li = prdtrnService.find(map);
		for(BCfgdefPrdtrn prdtrn : li){
			put(prdtrn.getTrncod(), prdtrn);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefPrdtrn getNewData(Object key) throws CacheException {
		BCfgdefPrdtrn prdtrn = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("trncod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefPrdtrn> li = prdtrnService.find(map);
		if(li.size()>0){
			prdtrn = li.get(0);
			cacheMap.put(String.valueOf(key),prdtrn);
		}
		return prdtrn;
	}

}
