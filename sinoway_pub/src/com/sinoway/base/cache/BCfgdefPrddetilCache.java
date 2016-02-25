package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgrefPrddetil;
import com.sinoway.base.service.BCfgrefPrddetilService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefPrddetilCache")
public class BCfgdefPrddetilCache extends LRUCache{
	@Value("${BCfgdefPrddetilCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefPrddetilCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefPrddetilCache.defaultExpire:0}")
	private long defaultExpire;
	
	@Autowired
	private BCfgrefPrddetilService prddetilService;
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
		List<BCfgrefPrddetil> li = prddetilService.find(map);
		for(BCfgrefPrddetil prddetil : li){
			put(prddetil.getPrdcod()+"_"+prddetil.getTrncod(), prddetil);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgrefPrddetil getNewData(Object key) throws CacheException {
		BCfgrefPrddetil prddetil = null;
		Map<String,Object> map = new HashMap<String,Object>();
		String[] keys = String.valueOf(key).split("_");
		map.put("prdcod",keys[0]);
		map.put("trncod",keys[1]);
		map.put("sta", "1");
		List<BCfgrefPrddetil> li = prddetilService.find(map);
		if(li.size()>0){
			prddetil =li.get(0);
		}
		return prddetil;
	}

}
