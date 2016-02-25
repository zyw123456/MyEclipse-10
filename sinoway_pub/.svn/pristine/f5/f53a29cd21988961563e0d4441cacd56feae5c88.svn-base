package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefOrginfo;
import com.sinoway.base.service.BCfgdefOrginfoService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefOrginfoCache")
public class BCfgdefOrginfoCache extends LRUCache{
	@Value("${BCfgdefOrginfoCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefOrginfoCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefOrginfoCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefOrginfoService orginfoService;
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
		List<BCfgdefOrginfo> li = orginfoService.find(map);
		for(BCfgdefOrginfo orginfo : li){
			put(orginfo.getOrgno(), orginfo);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefOrginfo getNewData(Object key) throws CacheException {
		BCfgdefOrginfo orginfo = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orgno",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefOrginfo> li = orginfoService.find(map);
		if(li.size()>0){
			orginfo = li.get(0);
		}
		return orginfo;
	}

}
