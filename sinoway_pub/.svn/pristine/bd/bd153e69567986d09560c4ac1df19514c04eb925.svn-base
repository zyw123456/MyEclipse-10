package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefMetatrn;
import com.sinoway.base.service.BCfgdefMetatrnService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefMetatrnCache")
public class BCfgdefMetatrnCache extends LRUCache{
	@Value("${BCfgdefFntkeyinfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefFntkeyinfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefFntkeyinfoCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefMetatrnService metatrnService;
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
		List<BCfgdefMetatrn> li = metatrnService.find(map);
		for(BCfgdefMetatrn metatrn : li){
			put(metatrn.getTrncod(), metatrn);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefMetatrn getNewData(Object key) throws CacheException {
		BCfgdefMetatrn metatrn = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("trncod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefMetatrn> li = metatrnService.find(map);
		if(li.size()>0){
			metatrn = li.get(0);
		}
		return metatrn;
	}
	
}
