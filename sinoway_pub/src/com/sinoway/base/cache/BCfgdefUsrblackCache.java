package com.sinoway.base.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinoway.base.entity.BCfgdefUsrblack;
import com.sinoway.base.service.BCfgdefUsrblackService;
import com.sinoway.common.cache.impl.ICacheMapper;
import com.sinoway.common.cache.impl.LRUCache;
import com.sinoway.common.exception.CacheException;

@Component("bCfgdefUsrblackCache")
public class BCfgdefUsrblackCache extends LRUCache{
	@Value("${BCfgdefUsrblackCache.isOpen:false}")
	private boolean isOpen;
	@Value("${BCfgdefUsrblackCache.cacheSize:0}")
	private int cacheSize;
	@Value("${BCfgdefUsrblackCache.defaultExpire:0}")
	private long defaultExpire;
	@Autowired
	private BCfgdefUsrblackService usrblackService;
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
		List<BCfgdefUsrblack> li = usrblackService.find(map);
		for(BCfgdefUsrblack usrBlack : li){
			put(usrBlack.getUsrid(), usrBlack);
		}
	}

	public synchronized  void reloadAll() {
		clear();
		loadAll();
	}
	
	
	public synchronized BCfgdefUsrblack getNewData(Object key) throws CacheException {
		BCfgdefUsrblack usrblack = null;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usrid",String.valueOf(key));
		map.put("sta", "1");
		List<BCfgdefUsrblack> li = usrblackService.find(map);
		if(li.size()>0){
			usrblack = li.get(0);
		}
		return usrblack;
	}

}
