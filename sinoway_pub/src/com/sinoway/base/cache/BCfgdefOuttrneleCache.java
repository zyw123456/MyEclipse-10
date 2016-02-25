package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefOuttrnele;
import com.sinoway.base.service.BCfgdefOuttrneleService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefOuttrneleCache")
public class BCfgdefOuttrneleCache extends LRUCache{
	@Value("${BCfgdefOuttrneleCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefOuttrneleCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefOuttrneleCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefOuttrneleService outtrneleService;
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
		List<BCfgdefOuttrnele> li = outtrneleService.find(map);
		for(BCfgdefOuttrnele outtrnele : li){
			put(outtrnele.getOutelecod(), outtrnele);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefOuttrnele getNewData(Object key) throws CacheException {
		BCfgdefOuttrnele outtrnele = null; 
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("outelecod",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefOuttrnele> li = outtrneleService.find(map);
		if(li.size()>0){
			outtrnele = li.get(0);
		}
		return outtrnele;
	}

}
